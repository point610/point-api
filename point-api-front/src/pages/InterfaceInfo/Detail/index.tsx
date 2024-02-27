import APITextInfo from '@/pages/InterfaceInfo/Detail/components/APIText';
import AuthorInfo from '@/pages/InterfaceInfo/Detail/components/AuthorInfo';
import ErrorCode from '@/pages/InterfaceInfo/Detail/components/ErrorCode';
import {
  getInterfaceInfoByIdUsingGet
} from '@/services/backend/interfaceInfoController';
import {useModel, useParams} from '@@/exports';
import {PageContainer} from '@ant-design/pro-components';
import {Badge, Card, Col, Image, message, Row, Space, Tabs, Tag, Progress, Typography, Button} from 'antd';
import moment from 'moment';
import React, {useEffect, useState} from 'react';
import {
  addLoginUserInterfaceUsingPost,
  getLoginUserInterfaceUsingPost
} from "@/services/backend/userInterfaceInfoController";
import {DownloadOutlined} from "@ant-design/icons";
import Invoke from "@/pages/InterfaceInfo/Detail/components/Invoke";

/**
 * 生成器详情页
 * @constructor
 */
const GeneratorDetailPage: React.FC = () => {
  const {id} = useParams();

  const [loading, setLoading] = useState<boolean>(false);
  const [data, setData] = useState<API.InterfaceInfoVO>({});
  const [userInterfaceInfoDate, setUserInterfaceInfoDate] = useState<API.UserInterfaceInfo>({});
  const {initialState} = useModel('@@initialState');
  const {currentUser} = initialState ?? {};
  const my = currentUser?.id === data?.userId;

  /**
   * 加载数据
   */
  const loadData = async () => {
    if (!id) {
      return;
    }
    setLoading(true);
    try {
      const res = await getInterfaceInfoByIdUsingGet({
        id,
      });
      setData(res.data || {});

      const restemp = await getLoginUserInterfaceUsingPost({
        id,
      });
      setUserInterfaceInfoDate(restemp.data || {});

    } catch (error: any) {
      message.error('获取数据失败，' + error.message);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData();
  }, [id]);

   /**
   * 加载接口剩余调用次数
   */
  const loadUserInterfaceInfo = async () => {
    try {
      const restemp = await getLoginUserInterfaceUsingPost({
        id,
      });
      setUserInterfaceInfoDate(restemp.data || {});
    } catch (error: any) {
      message.error('获取数据失败，' + error.message);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData();
  }, [id]);

  /**
   * 提交
   * @param values
   */
  const requestLimit = async () => {
    try {
      const restemp = await addLoginUserInterfaceUsingPost({
        id,
      });
      setUserInterfaceInfoDate(restemp.data || {});
    } catch (error: any) {
      message.error('获取数据失败，' + error.message);
    }
  };



  return (
    <PageContainer title={<></>} loading={loading}>
      <Card title="接口信息" bordered={false} headStyle={{background: "#eae5f1"}}>
        <Row justify="space-between" gutter={[32, 32]}>
          <Col flex="auto">
            <Space size="large" align="center">
              <Typography.Title level={3}>{data.name}</Typography.Title>
            </Space>
            <Typography.Paragraph type="secondary">名称：{data.name}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">描述：{data.description}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">接口地址：{data.url}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">请求方式：<Tag color="blue">{data.method}</Tag></Typography.Paragraph>
            <Typography.Paragraph type="secondary">接口状态：
              {data.status === 1 ? <Badge status="processing" text={"已上线"}/> :
                <Badge status="default" text={"已下线"}/>}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">
              创建时间：{moment(data.createTime).format('YYYY-MM-DD HH:mm:ss')}
            </Typography.Paragraph>
            <div style={{marginBottom: 24}}/>
          </Col>
          <Col flex="320px">
            <Image src={data.picture}/>
          </Col>
        </Row>
      </Card>
      <p></p>
      {/*接口剩余调用次数*/}
      <Card title="接口剩余调用次数" bordered={false} headStyle={{background: "#eae5f1"}}
            extra={<Button onClick={requestLimit}>申请调用次数</Button>}>
        <Progress
          percent={userInterfaceInfoDate.leftNum}
          status="active"/>
      </Card>
      <p></p>
      {/*其他信息*/}
      <Card>
        <Tabs
          size="large"
          defaultActiveKey={'fileConfig'}
          onChange={() => {
          }}
          items={[
            {
              key: 'apitext',
              label: 'API文档',
              children: <APITextInfo data={data}/>,
            },
            {
              key: 'invoke',
              label: '在线调用',
              children: <Invoke data={data} callback={loadUserInterfaceInfo}/>,
            },
            {
              key: 'erroecode',
              label: '错误码',
              children: <ErrorCode data={data}/>,
            },
            {
              key: 'userInfo',
              label: '作者信息',
              children: <AuthorInfo data={data}/>,
            },
          ]}
        />
      </Card>
      <p></p>
      {/* 开发者 SDK */}
      <Card title="开发者 SDK" bordered={false} headStyle={{background: "#eae5f1"}}>
        <Row justify="space-between" gutter={[32, 32]}>
          <Col flex="auto">
            <Button size={"large"} target="_blank" href={"https://github.com/point610/point-api-sdk"}
                    icon={<DownloadOutlined/>}>
              Java SDK
            </Button>
          </Col>
        </Row>
      </Card>
    </PageContainer>
  );
};

export default GeneratorDetailPage;
