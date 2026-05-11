import request from '@/utils/request'

export const getCarouselList = () => request.get('/carousel/list')
export const getNewsLatest = () => request.get('/news/latest')
export const getIntroductionCatalog = () => request.get('/introduction/catalog')
export const getScenicRecommend = () => request.get('/scenic/recommend')
