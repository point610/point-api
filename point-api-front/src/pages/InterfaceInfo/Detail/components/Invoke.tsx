import {Button, Card, Cascader, Input, message, Table, Typography} from 'antd';
import React, {useState} from 'react';
import {invokeInterfaceInfoUsingPost,} from "@/services/backend/interfaceInfoController";

const {Title, Paragraph, Text, Link} = Typography;

const {Search} = Input;

interface Props {
  data: API.InterfaceInfoVO;
  callback: any;
}

const nullSource = [
  {
    paramName: '无',
    type: '无',
  },
];

// 对象映射，存储每种类型对应的默认值
const defaultValues = {
  'string': "",
  'int': 0,
  'long': 0,
  // 添加其他类型的默认值设置
};
/**
 * 作者信息
 * @constructor
 */
const Invoke: React.FC<Props> = (props) => {

  const {data, callback,} = props;
  const id = data.id;
  const [loading, setLoading] = useState<boolean>(false);
  const [resDate, setResData] = useState<API.BaseResponseObject_>();

  const user = data?.userVO;

  if (!user) {
    return <></>;
  }


  const requestParams = data?.requestParams;
  const requestParamsSource = ((requestParams === "" || requestParams === null) ? nullSource : JSON.parse(requestParams));
  const [dataSource, setDataSource] = ((requestParams === "" || requestParams === null) ? "" : useState(JSON.parse(requestParams).reduce((obj, item) => {
      // 根据类型设置默认值
      obj[item.paramName] = defaultValues[item.type as keyof typeof defaultValues];
      return obj;
    }, {})))
  ;

  const handleInputChange = (e, record) => {
    if (requestParams !== "") {
      const {value} = e.target;
      // 遍历 dataSource 对象的键
      Object.keys(dataSource).forEach(key => {
        // 检查当前键是否匹配目标 paramName
        if (key === record.paramName) {
          // 如果匹配，则更新值
          dataSource[key] = value;
        }
      });
    }
  };

  const columns = [
    {
      title: '参数名称',
      dataIndex: 'paramName',
      key: 'paramName'
    },
    {
      title: '类型',
      dataIndex: 'type',
      key: 'type'
    },
    {
      title: '请求值',
      key: 'userRequestParams',
      render: (record) => ((requestParams === "") ? <p></p> :
          <Input value={record.key} onChange={(e) => handleInputChange(e, record)}/>
      )
    }
  ];

  /**
   * 搜索
   */
  const doSubmit = async () => {
    setLoading(true);
    try {
      const userRequestParams = JSON.stringify(dataSource);
      const res = await invokeInterfaceInfoUsingPost(
        {id, userRequestParams} as API.InterfaceInfoInvokeRequest
      );
      setResData(res);
      console.log(res)
      callback();
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
      <Table rowKey="key" dataSource={requestParamsSource} pagination={false} columns={columns}/>

      {/*返回结果*/}
      <Paragraph>
        <blockquote>{"返回结果"}</blockquote>
      </Paragraph>
      <Card style={{background: "#f3f2fa"}}>
        <pre>{JSON.stringify(resDate, null, 2)}</pre>
      </Card>
    </div>
  );
};

export default Invoke;
