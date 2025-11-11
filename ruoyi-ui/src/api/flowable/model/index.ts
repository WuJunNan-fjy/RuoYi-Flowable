import request from '@/utils/request';

export type ProcessDefinitionVO = {
    id: string
    version: number
    deploymentTIme: string
    suspensionState: number
    formType?: number
}

export type ModelVO = {
    id: number
    key: string
    name: string
}

/**
 * 创建流程模型
 * @param data
 */
export const createModel = async (data: ModelVO) => {
    return await request.post({ url: '/flowable/model/create', data })
}
