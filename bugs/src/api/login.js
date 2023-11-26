import BugsAxios from "@/utils/BugsAxios";

export const getUserById = (args) => {
    return BugsAxios.get('/UserController?id=test01');
}

export const login = (args) => {
    return BugsAxios.post('/UserController/login', args);
}

export const register = (args) => {
    return BugsAxios.post('/UserController/register', args);
}