import { request } from '../utils/request.js'

export function login(data) {
    return request({
        url: '/user/login',
        method: 'post',
        data
    })
}
export function loginByOffline(code) {
    return request({
        url: '/user/login_offline/' + code,
        method: 'post',
    })
}

export function register(form) {
    return request({
        url: '/user/register',
        method: 'post',
        data: form
    })
}

export function updatePassword(data) {
    return request({
        url: '/user/password',
        method: 'put',
        data
    })
}

export function getUserInfo() {
    return request({
        url: '/user/info',
        method: 'get',
    })
}

export function list(data) {
    return request({
        url: '/user/list',
        method: 'get',
        data
    })
}

export function deleteUser(id) {
    return request({
        url: '/user/' + id,
        method: 'delete',
    })
}
