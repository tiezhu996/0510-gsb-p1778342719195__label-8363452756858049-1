import request from '@/utils/request'

export const getFoodFeatured = () => request.get('/food/featured')
export const getFoodRanking = () => request.get('/food/ranking')
export const getFoodDetail = (id) => request.get(`/food/${id}`)
export const increaseFoodPopularity = (id) => request.put(`/food/${id}/popularity`)
