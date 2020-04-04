package com.example.domain.service.todo;

import com.example.domain.model.todo.Todo;

import java.util.Collection;

public interface TodoService {

    /**
     * Todoの全件取得
     *
     * @return TODOの一覧
     */
    Collection<Todo> findAll();

    /**
     * Todoの新規作成
     *
     * @param todo 新規作成するTodo(タイトルは必須)
     * @return 作成されたTodo
     */
    Todo create(Todo todo);

    /**
     * Todoの完了
     *
     * @param todoId 未完了のTodoの内部願望
     * @return 完了に更新されたTodo
     * 例外 指定された内部番号の未完了のTodoが存在しない場合
     *
     */
    Todo finish(String todoId);

    /**
     * Todoの物理削除
     *
     * @param todoId 削除するTodoの内部番号
     * 例外 指定された内部番号のTodoが存在しない場合
     */
    void delete(String todoId);

}
