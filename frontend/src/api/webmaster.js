import request from '@/utils/request'

export const getWebmasterInfo = () => request.get('/webmaster/info')
