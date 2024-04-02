import {
    request
} from '../utils/request.js'

export function insertGoods(data) {
    return request({
        url: '/goods',
        method: 'post',
        data,
    })
}

export function listByUserId() {
    return request({
        url: '/goods/list/user',
        method: 'get',
    })
}
export function listAll() {
    return request({
        url: '/goods/list',
        method: 'get',
    })
}


export function getById({ id }) {
    return request({
        url: '/goods/' + id,
        method: 'get',
    })
}

export function deleteGoods({ id }) {
    return request({
        url: '/goods/' + id,
        method: 'delete',
    })
}
