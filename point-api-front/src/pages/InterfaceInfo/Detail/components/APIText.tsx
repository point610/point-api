import {Avatar, Card, Table, Typography} from 'antd';

const {Title, Paragraph, Text, Link} = Typography;
import React from 'react';

interface Props {
  data: API.InterfaceInfoVO;
}

const nullSource = [
  {
    paramName: '无',
    type: '无',
    required: '无',
    description: '无',
  },
];

/**
 * 作者信息
 * @constructor
 */
const APITextInfo: React.FC<Props> = (props) => {
  const {data} = props;

  const user = data?.userVO;
  const requestParams = data?.requestParams;
  const requestParamsSource = ((requestParams === "" || requestParams === null) ? nullSource : JSON.parse(requestParams));
  const responseParams = data?.responseParams;
  const responseParamsSource = ((responseParams === "" || responseParams === null) ? nullSource : JSON.parse(responseParams));

  const requestColumns = [
    {
      title: '参数名称',
      dataIndex: 'paramName',
      key: 'paramName',
    },
    {
      title: '必选',
      dataIndex: 'required',
      key: 'required',
    },
    {
      title: '类型',
      dataIndex: 'type',
      key: 'type',
    },
    {
      title: '描述',
      dataIndex: 'description',
      key: 'description',
    },
  ];
  const responseColumns = [
    {
      title: '参数名称',
      dataIndex: 'paramName',
      key: 'paramName',
    },
    {
      title: '类型',
      dataIndex: 'type',
      key: 'type',
    },
    {
      title: '描述',
      dataIndex: 'description',
      key: 'description',
    },
  ];

  if (!user) {
    return <></>;
  }

  return (
    <div>
      {/*请求参数*/}
      <Paragraph>
        <blockquote>{"请求参数"}</blockquote>
      </Paragraph>
      <Table dataSource={requestParamsSource} pagination={false}
             columns={requestColumns}/>

      {/*响应参数*/}
      <Paragraph>
        <blockquote>{"响应参数"}</blockquote>
      </Paragraph>
      <Table dataSource={responseParamsSource} pagination={false} columns={responseColumns}/>

      {/*返回示例*/}
      <Paragraph>
        <blockquote>{"返回示例"}</blockquote>
      </Paragraph>
      <Card>
        <pre>{data.responseExample}</pre>
      </Card>
    </div>
  );
};

export default APITextInfo;
