import {
  deleteTodoById,
  getAllTodosResponse,
  TodoResponse,
} from "./spring-api";
import { useMutation } from "@tanstack/react-query";
import { updateTodo } from "./spring-api";
import { X } from "lucide-react";
import { queryClient } from "./main";

export default function TodoComponent({
  optimistic = false,
  ...todo
}: { optimistic?: boolean } & TodoResponse) {
  const { mutate: toggleCompleted, isPending: togglingTodo } = useMutation({
    mutationFn: () =>
      updateTodo(todo.id, { ...todo, completed: !todo.completed }),
    onSettled: () => {
      queryClient.setQueryData(
        ["todos-list"],
        (todos: getAllTodosResponse): getAllTodosResponse => ({
          ...todos,
          data: todos.data.map((t) =>
            t.id === todo.id ? { ...t, completed: !t.completed } : t
          ),
        })
      );
      queryClient.invalidateQueries({ queryKey: ["todos-list"] });
    },
  });

  const { mutate: deleteTodo, isPending: deletingTodo } = useMutation({
    mutationFn: () => deleteTodoById(todo.id),
    onSettled: () => {
      queryClient.setQueryData(
        ["todos-list"],
        (todos: getAllTodosResponse): getAllTodosResponse => ({
          ...todos,
          data: todos.data.filter((t) => t.id !== todo.id),
        })
      );
      queryClient.invalidateQueries({ queryKey: ["todos-list"] });
    },
  });

  return (
    <li className="flex flex-row gap-4 items-center">
      <input
        type="checkbox"
        checked={togglingTodo ? !todo.completed : todo.completed}
        disabled={togglingTodo || deletingTodo || optimistic}
        className="form-control checkbox"
        onChange={() => {
          toggleCompleted();
        }}
      />
      <span className="min-w-48">{todo.title}</span>
      <button
        className="btn btn-circle btn-ghost"
        disabled={deletingTodo || optimistic}
        onClick={() => {
          deleteTodo();
        }}
      >
        <X />
      </button>
    </li>
  );
}
