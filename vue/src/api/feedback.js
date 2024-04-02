import request from '@/utils/request'


export function insertFeedback(data) {
    return request({
        url: '/feedback',
        method: 'post',
        data,
    })
}

export function updateFeedback(data) {
    return request({
        url: '/feedback',
        method: 'put',
        data,
    })
}
export function listFeedback() {
    return request({
        url: '/feedback/list',
        method: 'get',
    })
}

export function listByUserId() {
    return request({
        url: '/feedback/list/user',
        method: 'get',
    })
}

export function deleteFeedback(id) {
    return request({
        url: '/feedback/' + id,
        method: 'delete',
    })
}

