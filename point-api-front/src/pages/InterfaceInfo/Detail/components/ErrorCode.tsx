import {Table} from 'antd';
import React from 'react';
import {Typography} from 'antd';

const {Title, Paragraph, Text, Link} = Typography;

interface Props {
  data: API.InterfaceInfoVO;
}

/**
 * 作者信息
 * @constructor
 */
const ErrorTable: React.FC<Props> = () => {

  const dataSource = [
    {
      paramName: 'SUCCESS',
      errorCode: '0',
      description: 'ok',
    },
    {
      paramName: 'PARAMS_ERROR',
      errorCode: '40000',
      description: '请求参数错误',
    },
    {
      paramName: 'NO_AUTH_ERROR',
      errorCode: '40101',
      description: '无权限',
    },
    {
      paramName: 'FORBIDDEN_ERROR',
      errorCode: '40300',
      description: '禁止访问',
    },
    {
      paramName: 'NOT_FOUND_ERROR',
      errorCode: '40400',
      description: '请求数据不存在',
    },
    {
      paramName: 'SYSTEM_ERROR',
      errorCode: '50000',
      description: '系统内部异常',
    },
    {
      paramName: 'OPERATION_ERROR',
      errorCode: '50001',
      description: '操作失败',
    },
  ];

  const columns = [
    {
      title: '参数名称',
      dataIndex: 'paramName',
      key: 'paramName',
    },
    {
      title: '错误码',
      dataIndex: 'errorCode',
      key: 'errorCode',
    },
    {
      title: '描述',
      dataIndex: 'description',
      key: 'description',
    },
  ];

  return (
    <div>
      <Paragraph>
        <blockquote>{"错误码"}</blockquote>
      </Paragraph>
      <Table dataSource={dataSource}   pagination={false} columns={columns}/>
    </div>
  );
};

export default ErrorTable;
