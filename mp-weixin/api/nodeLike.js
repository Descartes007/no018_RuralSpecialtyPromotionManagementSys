import {
  request
} from '../utils/request.js'



export function insert(data) {
  return request({
    url: '/node/like',
    method: 'post',
    data,
  })
}
