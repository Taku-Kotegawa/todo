-- TODO テーブル --
create table if not exists todo (
    todo_id varchar(36) primary key,
    todo_title varchar(30),
    finished boolean,
    created_at timestamp
)
;

--テーブルにコメント
COMMENT ON TABLE todo IS 'Todo';

--カラムにコメント
COMMENT ON COLUMN todo.todo_id IS 'Todo内部番号';
COMMENT ON COLUMN todo.todo_title IS 'Todoタイトル';
COMMENT ON COLUMN todo.finished IS '完了フラグ';
COMMENT ON COLUMN todo.created_at IS '作成日時';



