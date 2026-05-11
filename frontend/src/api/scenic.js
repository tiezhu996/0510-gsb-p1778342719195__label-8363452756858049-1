import request from '@/utils/request'

export const getScenicList = (params) => request.get('/scenic/list', { params })
export const getScenicDetail = (id) => request.get(`/scenic/${id}`)
export const increaseScenicView = (id) => request.put(`/scenic/${id}/view`)
