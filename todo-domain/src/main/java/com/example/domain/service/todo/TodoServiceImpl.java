package com.example.domain.service.todo;

import com.example.domain.model.todo.Todo;
import com.example.domain.model.todo.TodoExample;
import com.example.domain.repository.todo.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


/**
 * @inheritdoc
 */
@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final long MAX_UNFINISHED_COUNT = 5;

    @Inject
    TodoRepository todoRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<Todo> findAll() {
        return todoRepository.selectByExample(new TodoExample());
    }

    @Override
    public Todo create(Todo todo) {

        ResultMessages messages = ResultMessages.error();

        // 未完了のTODOの件数を取得
        // MyBatisGeneratorのExampleクラスを使う。
        TodoExample example = new TodoExample();
        example.or().andFinishedEqualTo(false);
        long unfinishedCount = todoRepository.countByExample(example);

        // 登録されている未完了件数が上限(以上)の場合に例外をスロー
        if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
            messages.add(ResultMessage
                    .fromText("[E001] The count of un-finished Todo must not be over "
                            + MAX_UNFINISHED_COUNT + "."));
            throw new BusinessException(messages);
        }

        // 引数で指定されたTodoの件名が取得できない場合、Todoは作成しない。
        if (todo == null || todo.getTodoTitle() == null || todo.getTodoTitle().isEmpty()) {
            messages.add(ResultMessage
                    .fromText("[Exxx] Cannot Create New Todo. reason: titile is null or blank."));
            throw new BusinessException(messages);
        }

        todo.setTodoId(UUID.randomUUID().toString());
        todo.setCreatedAt(LocalDateTime.now());
        todo.setFinished(false);

        todoRepository.insert(todo);
        return todoRepository.selectByPrimaryKey(todo.getTodoId());

    }

    @Override
    public Todo finish(String todoId) {
        ResultMessages messages = ResultMessages.error();

        Todo todo = findOne(todoId);
        if (todo.getFinished()) {
            messages.add(ResultMessage
                    .fromText("[E002] The requested Todo is already finished. (id="
                            + todoId + ")"));
            throw new BusinessException(messages);
        }

        todo.setFinished(true);
        int i = todoRepository.updateByPrimaryKey(todo);

        if (i < 1) {
            messages.add(ResultMessage
                    .fromText("[Exx] Cannot Finished Todo. reason: unknown"));
            throw new BusinessException(messages);
        }
        return todoRepository.selectByPrimaryKey(todo.getTodoId());
    }

    @Override
    public void delete(String todoId) {
        Todo todo = findOne(todoId);
        todoRepository.deleteByPrimaryKey(todo.getTodoId());
    }

    /**
     * 指定された内部番号のTodoを１件取得する
     * @param todoId 内部番号
     * @return Todoエンティティ
     * 例外 指定された内部番号のTodoが存在しない場合
     */
    private Todo findOne(String todoId) {
        Todo todo = todoRepository.selectByPrimaryKey(todoId);
        if (todo == null) {
            // (5)
            ResultMessages messages = ResultMessages.error();
            messages.add(ResultMessage
                    .fromText("[E404] The requested Todo is not found. (id="
                            + todoId + ")"));
            // (6)
            throw new ResourceNotFoundException(messages);
        }
        return todo;
    }

}
