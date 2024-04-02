import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
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
