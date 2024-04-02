import {
    request
} from '../utils/request.js'



export function insert(data) {
    return request({
        url: '/node/comment',
        method: 'post',
        data,
    })
}