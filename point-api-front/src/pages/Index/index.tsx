import {listInterfaceInfoVoByPageUsingPost} from '@/services/backend/interfaceInfoController';
import {PageContainer, ProFormText, QueryFilter} from '@ant-design/pro-components';
import {Badge, Card, Flex, Image, Input, List, message, Tabs, Typography} from 'antd';
import moment from 'moment';
import React, {useEffect, useState} from 'react';
import {Link} from "umi";

/**
 * 默认分页参数
 */
const DEFAULT_PAGE_PARAMS: PageRequest = {
  current: 1,
  pageSize: 8,
  sortField: 'createTime',
  sortOrder: 'descend',
};

/**
 * 主页
 * @constructor
 */
const IndexPage: React.FC = () => {
  const [loading, setLoading] = useState<boolean>(true);
  const [dataList, setDataList] = useState<API.InterfaceInfoVO[]>([]);
  const [total, setTotal] = useState<number>(0);
  // 搜索条件
  const [searchParams, setSearchParams] = useState<API.InterfaceInfoQueryRequest>({
    ...DEFAULT_PAGE_PARAMS,
  });

  /**
   * 搜索
   */
  const doSearch = async () => {
    setLoading(true);
    try {
      const res = await listInterfaceInfoVoByPageUsingPost(searchParams);
      setDataList(res.data?.records ?? []);
      setTotal(Number(res.data?.total) ?? 0);
    } catch (error: any) {
      message.error('获取数据失败，' + error.message);
    }
    setLoading(false);
  };

  useEffect(() => {
    doSearch();
  }, [searchParams]);


  return (
    <PageContainer title={<></>}>
      <Flex justify="center">
        <Input.Search
          style={{
            width: '40vw',
            minWidth: 320,
          }}
          placeholder="搜索接口"
          allowClear
          enterButton="搜索"
          size="large"
          onChange={(e) => {
            searchParams.description = e.target.value;
          }}
          onSearch={(value: string) => {
            setSearchParams({
              ...searchParams,
              ...DEFAULT_PAGE_PARAMS,
              description: value,
            });
          }}
        />
      </Flex>
      <div style={{marginBottom: 16}}/>

      <Tabs
        size="large"
        defaultActiveKey="newest"
        items={[
          {
            key: 'newest',
            label: '最新',
          },
          {
            key: 'recommend',
            label: '推荐',
          },
        ]}
        onChange={() => {
        }}
      />

      <QueryFilter
        span={12}
        labelWidth="auto"
        labelAlign="left"
        defaultCollapsed={false}
        style={{padding: '16px 0'}}
        onFinish={async (values: API.InterfaceInfoQueryRequest) => {
          setSearchParams({
            ...DEFAULT_PAGE_PARAMS,
            // @ts-ignore
            ...values,
          });
        }}
      >
        <ProFormText label="名称" name="name"/>
        <ProFormText label="描述" name="description"/>
      </QueryFilter>

      <div style={{marginBottom: 24}}/>

      <List<API.InterfaceInfoVO>
        rowKey="id"
        loading={loading}
        grid={{
          gutter: 16,
          xs: 1,
          sm: 2,
          md: 3,
          lg: 3,
          xl: 4,
          xxl: 4,
        }}
        dataSource={dataList}
        pagination={{
          current: searchParams.current,
          pageSize: searchParams.pageSize,
          total,
          onChange(current: number, pageSize: number) {
            setSearchParams({
              ...searchParams,
              current,
              pageSize,
            });
          },
        }}
        renderItem={(data) => (
          <List.Item>
            <Link to={`/interfaceinfo/detail/${data.id}`}>
              <Badge count={data.totalNum} overflowCount={999999} offset={[-15, 10]}>
                <Card hoverable cover={<Image alt={data.name} src={data.picture}/>}>
                  <Card.Meta
                    title={<a>{data.name}</a>}
                    description={
                      <Typography.Paragraph ellipsis={{rows: 2}} style={{height: 44}}>
                        {data.description}
                      </Typography.Paragraph>
                    }
                  />
                  <Flex justify="space-between" align="center">
                    <Typography.Text type="secondary" style={{fontSize: 12}}>
                      {moment(data.createTime).fromNow()}
                    </Typography.Text>
                  </Flex>
                </Card>
              </Badge>
            </Link>
          </List.Item>
        )}
      />
    </PageContainer>
  );
};

export default IndexPage;
