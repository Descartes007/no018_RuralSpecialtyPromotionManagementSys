import {
    request
} from '../utils/request.js'



export function insertUserGoods(data) {
    return request({
        url: '/user/goods',
        method: 'post',
        data,
    })
}
export function listByPayUserId() {
    return request({
        url: '/user/goods/list/pay_user',
        method: 'get',
    })
}

export function listByMerchantId() {
    return request({
        url: '/user/goods/list/merchant_id',
        method: 'get',
    })
}
