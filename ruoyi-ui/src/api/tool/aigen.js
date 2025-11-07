import request from '@/utils/request'

// 查询AI生成任务列表
export function listTask(query) {
  return request({
    url: '/tool/aigen/task/list',
    method: 'get',
    params: query
  })
}

// 查询AI生成任务详细
export function getTask(taskId) {
  return request({
    url: '/tool/aigen/task/' + taskId,
    method: 'get'
  })
}

// 新增AI生成任务
export function addTask(data) {
  return request({
    url: '/tool/aigen/task',
    method: 'post',
    data: data
  })
}

// 修改AI生成任务
export function updateTask(data) {
  return request({
    url: '/tool/aigen/task',
    method: 'put',
    data: data
  })
}

// 删除AI生成任务
export function delTask(taskId) {
  return request({
    url: '/tool/aigen/task/' + taskId,
    method: 'delete'
  })
}

// 生成代码
export function genCode(taskId) {
  return request({
    url: '/tool/aigen/task/code/' + taskId,
    method: 'get'
  })
}

// 预览代码
export function previewCode(taskId) {
  return request({
    url: '/tool/aigen/task/preview/' + taskId,
    method: 'get'
  })
}