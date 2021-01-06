import axios from "axios";

function createInstance() {
  const instance = axios.create({
    baseURL: "http://localhost",
    headers: {
      "Content-Type": "application/json"
    }
  });
  return instance;
}

export { createInstance };
