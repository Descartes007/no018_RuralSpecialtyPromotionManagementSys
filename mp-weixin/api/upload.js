import { upload } from '../utils/request.js'

export function uploadImage(data) {
    return upload({
        url: '/upload/image',
        path: data.path,
        data
    })
}

export function uploadVideo(data) {
    return upload({
        url: '/upload/video',
        ...data
    })
}