package com.example.app.todo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Todoフォームビーン
 * @author taku
 */
@Getter
@Setter
public class TodoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public static interface TodoCreate {};
    public static interface TodoFinish {};
    public static interface TodoDelete {}

    /** Todo内部番号 */
    @NotNull(groups = { TodoFinish.class, TodoDelete.class })
    private String todoId;

    /** Todoタイトル */
    @NotNull(groups = { TodoCreate.class })
    @Size(min = 1, max = 30, groups = { TodoCreate.class })
    private String todoTitle;

}
