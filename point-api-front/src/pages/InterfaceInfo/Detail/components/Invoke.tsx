import {Avatar, Button, Card, Cascader, message, Table, Typography} from 'antd';

const {Title, Paragraph, Text, Link} = Typography;
import React, {useEffect, useState} from 'react';
import {Input, Space} from 'antd';
import {
  invokeInterfaceInfoUsingPost,
} from "@/services/backend/interfaceInfoController";

const {Search} = Input;

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
const Invoke: React.FC<Props> = (props) => {
  
  const {data} = props;
  const id = data.id;
  const [loading, setLoading] = useState<boolean>(false);
  const [resDate, setResData] = useState<API.BaseResponseObject_>();

  const user = data?.userVO;

  if (!user) {
    return <></>;
  }

  /**
   * 搜索
   */
  const doSubmit = async () => {
    setLoading(true);
    try {
      const res = await invokeInterfaceInfoUsingPost(
        {id, extraProp: ""} as API.InterfaceInfoInvokeRequest
      );
      setResData(res);
      console.log(res)
    } catch (error: any) {
      message.error('获取数据失败，' + error.message);
    }
    setLoading(false);
  };


  return (
    <div>
      {/*发送请求*/}
      <Paragraph>
        <blockquote>{"发送请求"}</blockquote>
      </Paragraph>
      <Search
        addonBefore={<Cascader disabled={true} placeholder={data.method?.toUpperCase()} style={{width: 100}}/>}
        enterButton={<Button loading={loading} type="primary" style={{width: 130}} onClick={doSubmit}>发送请求</Button>}
        size="large"
        value={data.url}
      />
      {/*请求参数*/}
      <Paragraph>
        <blockquote>{"请求参数"}</blockquote>
      </Paragraph>
      <Card>
        <pre>{JSON.stringify(resDate, null, 2)}</pre>
      </Card>
      {/*返回结果*/}
      <Paragraph>
        <blockquote>{"返回结果"}</blockquote>
      </Paragraph>
      <Card>
        <pre>{JSON.stringify(resDate, null, 2)}</pre>
      </Card>
    </div>
  );
};

export default Invoke;
