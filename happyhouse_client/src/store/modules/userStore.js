import { createInstance } from "./index.js";

const instance = createInstance();
// const config = {
//   headers: { "access-token": localStorage.getItem("access-token") }
// };

function login(user, success, fail) {
  instance.defaults.headers["access-token"] = window.localStorage.getItem(
    "access-token"
  );
  const body = {
    id: user.id,
    pass: user.pass
  };

  instance
    .post("user", JSON.stringify(body))
    .then(success)
    .catch(fail);
}

function Modify(user) {
  instance
    .put("user",JSON.stringify(user))
    .then((res) => console.log(res))
    .catch(exp => console.log(exp));
}

function Join(user) {
  instance
    .post(`user/join`, JSON.stringify(user))
    .then((res) => console.log(res))
    .catch(exp => console.log(exp));
}

function Delete(id) {
  console.log("ㅎㅇㅎㅇ");
  instance
    .delete("user/" + id)
    .then((res) => console.log(res))
    .catch(exp => console.log(exp));
}
async function findById(userid, success, fail) {
  instance.defaults.headers["access-token"] = window.localStorage.getItem(
    "access-token"
  );
  await instance
    .get(`/user/${userid}`)
    .then(success)
    .catch(fail);
}

export { login, findById, Join, Delete, Modify };
