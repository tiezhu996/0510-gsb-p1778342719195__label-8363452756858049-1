import request from '@/utils/request'

export const submitSurvey = (data) => request.post('/survey/submit', data)
