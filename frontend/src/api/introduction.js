import request from '@/utils/request'

export const getIntroductionList = () => request.get('/introduction/list')
export const getVideoList = () => request.get('/video/list')
