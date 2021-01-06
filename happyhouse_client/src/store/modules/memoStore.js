import { createInstance } from "./index.js";

const instance = createInstance();


function listMemo(boardno, success, fail) {
    instance
    .get(`memo/${boardno}`)
    .then(success)
    .catch(fail);
}

function registerMemo(memo, success, fail) {
    instance
    .post(`memo`, JSON.stringify(memo))
    .then(success)
    .catch(fail);
}

function deleteMemo(memono, success, fail) {
    instance
    .delete(`memo/${memono}`)
    .then(success)
    .catch(fail);
}

function modifyMemo(memo, success, fail) {
    instance
    .put(`memo`, JSON.stringify(memo))
    .then(success)
    .catch(fail);
}


export { listMemo, registerMemo, deleteMemo, modifyMemo};