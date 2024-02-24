import AuthorInfo from '@/pages/InterfaceInfo/Detail/components/AuthorInfo';
import {
  getInterfaceInfoByIdUsingGet
} from '@/services/backend/interfaceInfoController';
import { useModel, useParams} from '@@/exports';
import {PageContainer} from '@ant-design/pro-components';
import {Badge, Card, Col, Image, message, Row, Space, Tabs, Tag, Typography} from 'antd';
import moment from 'moment';
import React, {useEffect, useState} from 'react';

/**
 * 生成器详情页
 * @constructor
 */
const GeneratorDetailPage: React.FC = () => {
  const {id} = useParams();

  const [loading, setLoading] = useState<boolean>(false);
  const [data, setData] = useState<API.InterfaceInfoVO>({});
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
    } catch (error: any) {
      message.error('获取数据失败，' + error.message);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData();
  }, [id]);


  return (
    <PageContainer title={<></>} loading={loading}>
      <Card>
        <Row justify="space-between" gutter={[32, 32]}>
          <Col flex="auto">
            <Space size="large" align="center">
              <Typography.Title level={4}>{data.name}</Typography.Title>
            </Space>
            <Typography.Paragraph type="secondary">名称：{data.name}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">描述：{data.description}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">接口地址：{data.url}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">请求方式：<Tag color="blue">{data.method}</Tag></Typography.Paragraph>
            <Typography.Paragraph type="secondary">接口状态：
              {data.status === 0 ? <Badge status="processing" text={"已上线"}/> :
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
      <div style={{marginBottom: 16}}/>
      <Card>
        <Tabs
          size="large"
          defaultActiveKey={'fileConfig'}
          onChange={() => {
          }}
          items={[
            {
              key: 'apikey',
              label: 'API文档',
              children: <AuthorInfo data={data}/>,
            },
            {
              key: 'use',
              label: '在线调试',
              children: <AuthorInfo data={data}/>,
            },
            {
              key: 'userInfo',
              label: '作者信息',
              children: <AuthorInfo data={data}/>,
            },
          ]}
        />
      </Card>
    </PageContainer>
  );
};

export default GeneratorDetailPage;
