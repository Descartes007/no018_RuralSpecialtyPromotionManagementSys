import {
    request
} from '../utils/request.js'



export function insertNode(data) {
    return request({
        url: '/node',
        method: 'post',
        data,
    })
}

export function listByUserId() {
    return request({
        url: '/node/list/user',
        method: 'get',
    })
}

export function list(data) {
    return request({
        url: '/node/list',
        method: 'get',
        data
    })
}

export function listAll(data) {
    return request({
        url: '/node/list/all',
        method: 'get',
        data
    })
}

export function getById({ id }) {
    return request({
        url: '/node/' + id,
        method: 'get',
    })
}


export function deleteNode({ id }) {
    return request({
        url: '/node/' + id,
        method: 'delete',
    })
}

export function releaseNode(data) {
    return request({
        url: '/node/release',
        method: 'put',
        data,
    })
}

export function defeatNode(data) {
    return request({
        url: '/node/defeat',
        method: 'put',
        data,
    })
}
export function auditReleaseNode(data) {
    return request({
        url: '/node/audit_release',
        method: 'put',
        data,
    })
}

export function auditDefeatNode(data) {
    return request({
        url: '/node/audit_defeat',
        method: 'put',
        data,
    })
}