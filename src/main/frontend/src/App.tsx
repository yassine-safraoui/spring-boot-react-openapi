import { useState } from "react";
import {
  getAllTodos,
  addTodo,
} from "./spring-api/todos-controller/todos-controller";
import { useQuery, useMutation } from "@tanstack/react-query";
import { queryClient } from "./main";
import TodoComponent from "./Todo";

function App() {
  const {
    data: todos,
    isLoading: loadingTodos,
    isError: todosError,
  } = useQuery({
    queryKey: ["todos-list"],
    queryFn: getAllTodos,
  });

  const addTodoMutation = useMutation({
    mutationFn: addTodo,
    onSettled: () =>
      queryClient.invalidateQueries({ queryKey: ["todos-list"] }),
  });

  const [newTodo, setNewTodo] = useState("");

  if (!todos) {
    if (loadingTodos)
      return (
        <div className="flex w-full h-screen justify-center items-center">
          Loading...
        </div>
      );
    if (todosError)
      return (
        <div className="flex w-full h-screen justify-center items-center">
          Error fetching todos
        </div>
      );
    return (
      <div className="flex w-full h-screen justify-center items-center">
        Oops, something went wrong
      </div>
    );
  }

  return (
    <div className="flex flex-col gap-1 w-full h-screen justify-center items-center">
      {todos.data.map((todo) => (
        <TodoComponent key={todo.id} {...todo} />
      ))}
      {addTodoMutation.isPending && (
        <TodoComponent
          optimistic
          completed={false}
          id={-1}
          title={addTodoMutation.variables.title}
        />
      )}
      <div className="flex flex-row gap-2">
        <input
          className="form-control input input-bordered"
          type="text"
          value={newTodo}
          onChange={(e) => setNewTodo(e.target.value)}
        />
        <button
          className="btn btn-primary"
          onClick={async () => {
            addTodoMutation.mutate({
              title: newTodo,
              completed: false,
            });
            setNewTodo("");
          }}
        >
          Add Todo
        </button>
      </div>
    </div>
  );
}

export default App;
