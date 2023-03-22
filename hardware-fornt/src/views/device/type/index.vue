<template>
  <div class="container">
    <Breadcrumb :items="['硬件管理', '硬件类型配置']"/>
    <a-card class="general-card" title="硬件类型配置">
      <a-row>
        <a-col :flex="1">
          <a-form
              :label-col-props="{ span: 6 }"
              :model="formModel"
              :wrapper-col-props="{ span: 18 }"
              label-align="left"
          >
            <a-row :gutter="16">

              <a-col :span="8">
                <a-form-item
                    field="name"
                    label="名称"
                >
                  <a-input
                      v-model="formModel.name"
                      placeholder="请输入名称..."
                  />
                </a-form-item>
              </a-col>


            </a-row>
          </a-form>
        </a-col>
        <a-divider direction="vertical" style="height: 84px"/>
        <a-col :flex="'86px'" style="text-align: right">
          <a-space :size="18" direction="vertical">
            <a-button type="primary" @click="search">
              <template #icon>
                <icon-search/>
              </template>
              搜素
            </a-button>
            <a-button @click="reset">
              <template #icon>
                <icon-refresh/>
              </template>
              重置
            </a-button>
          </a-space>
        </a-col>
      </a-row>
      <a-divider style="margin-top: 0"/>
      <a-row style="margin-bottom: 16px">
        <a-col :span="12">
          <a-space>
            <a-button type="primary" @click="showAdd">
              <template #icon>
                <icon-plus/>
              </template>
              新增
            </a-button>

          </a-space>
        </a-col>
        <a-col
            :span="12"
            style="display: flex; align-items: center; justify-content: end"
        >
        </a-col>
      </a-row>
      <a-table
          :bordered="false"
          :columns="columns"
          :data="renderData"
          :pagination="pagination"
          row-key="id"
          size="medium"
          @page-change="onPageChange"
      >
        <template #sig="{ rowIndex }">

          <a-space>

            <a-space>
              <a-tag v-for="item in renderData[rowIndex].hardwareSignalEntities">{{
                  item.name
                }}
              </a-tag>
            </a-space>
          </a-space>
        </template>


        <template #operations="{ record }">

          <a-button size="small" type="text" @click="update(record)">
            修改
          </a-button>
          
        </template>

      </a-table>

    </a-card>


    <a-modal v-model:visible="showVisible" width="60%" @cancel="cancelAdd" @ok="submitAdd">
      <template #title>
        创建硬件类型
      </template>
      <a-form :model="createRequest" layout="vertical">
        <a-space :size="16" direction="vertical">
          <a-card class="general-card">
            <a-row :gutter="80">

              <a-col :span="8">
                <a-form-item
                    field="name"
                    label="名称"
                >
                  <a-input
                      v-model="createRequest.name"
                      placeholder="请输入用户名"
                  >
                  </a-input>
                </a-form-item>
              </a-col>
            </a-row>
            <a-row style="margin-bottom: 16px">
              <a-col :span="12">
                <a-space>
                  <a-button type="primary" @click="addRow">
                    <template #icon>
                      <icon-plus/>
                    </template>
                    新增
                  </a-button>

                </a-space>
              </a-col>
              <a-col
                  :span="12"
                  style="display: flex; align-items: center; justify-content: end"
              >
              </a-col>
            </a-row>
            <a-table :columns="columnsAdd" :data="createRequest.hardwareSignalEntities">
              <template #name="{ rowIndex }">
                <a-input v-model="createRequest.hardwareSignalEntities[rowIndex].name"/>
              </template>
              <template #sysKey="{ rowIndex }">
                <a-input v-model="createRequest.hardwareSignalEntities[rowIndex].sysKey"/>
              </template>
              <template #min="{ rowIndex }">
                <a-input-number v-model="createRequest.hardwareSignalEntities[rowIndex].min"
                                :step="0.1"/>
              </template>
              <template #max="{ rowIndex }">
                <a-input-number v-model="createRequest.hardwareSignalEntities[rowIndex].max"
                                :step="0.1"/>
              </template>
              <template #defaultWaringMin="{ rowIndex }">
                <a-input-number v-model="createRequest.hardwareSignalEntities[rowIndex].defaultWaringMin"
                                :step="0.1"/>
              </template>
              <template #defaultWaringMax="{ rowIndex }">
                <a-input-number v-model="createRequest.hardwareSignalEntities[rowIndex].defaultWaringMax"
                                :step="0.1"/>
              </template>

              <template #operations="{rowIndex}">
                <a-button size="small" type="text" @click="delArow(rowIndex)">
                  删除
                </a-button>
              </template>
            </a-table>
          </a-card>
        </a-space>
      </a-form>
    </a-modal>


    <a-modal v-model:visible="showUpdateVisible" width="60%" @cancel="cancelUpdate"
             @ok="submitUpdate">
      <template #title>
        修改硬件类型
      </template>
      <a-form :model="addForUpdateRow" layout="vertical">
        <a-space :size="16" direction="vertical">
          <a-card class="general-card">
            <a-row :gutter="80">

              <a-col :span="8">
                <a-form-item
                    field="name"
                    label="名称"
                >
                  <a-input
                      v-model="updateRequest.name"
                      placeholder="请输入用户名"
                  >
                  </a-input>
                </a-form-item>
              </a-col>
            </a-row>
            <a-row style="margin-bottom: 16px">
              <a-col :span="12">
                <a-space>
                  <a-button type="primary" @click="addForUpdateRow">
                    <template #icon>
                      <icon-plus/>
                    </template>
                    新增
                  </a-button>

                </a-space>
              </a-col>
              <a-col
                  :span="12"
                  style="display: flex; align-items: center; justify-content: end"
              >
              </a-col>
            </a-row>
            <a-table :columns="columnsAdd" :data="updateRequest.hardwareSignalEntities">
              <template #name="{ rowIndex }">
                <a-input v-model="updateRequest.hardwareSignalEntities[rowIndex].name"/>
              </template>
              <template #sysKey="{ rowIndex }">
                <a-input v-model="updateRequest.hardwareSignalEntities[rowIndex].sysKey"/>
              </template>
              <template #min="{ rowIndex }">
                <a-input-number v-model="updateRequest.hardwareSignalEntities[rowIndex].min"
                                :step="0.1"/>
              </template>
              <template #max="{ rowIndex }">
                <a-input-number v-model="updateRequest.hardwareSignalEntities[rowIndex].max"
                                :step="0.1"/>
              </template>
              <template #defaultWaringMin="{ rowIndex }">
                <a-input-number v-model="updateRequest.hardwareSignalEntities[rowIndex].defaultWaringMin"
                                :step="0.1"/>
              </template>
              <template #defaultWaringMax="{ rowIndex }">
                <a-input-number v-model="updateRequest.hardwareSignalEntities[rowIndex].defaultWaringMax"
                                :step="0.1"/>
              </template>

              <template #operations="{rowIndex}">
                <a-button size="small" type="text" @click="delForUpdateArow(rowIndex)">
                  删除
                </a-button>
              </template>
            </a-table>
          </a-card>
        </a-space>
      </a-form>
    </a-modal>


    <a-modal v-model:visible="showViewVisible" width="60%" @cancel="showSee" @ok="showSee">
      <template #title>
        硬件类型详情
      </template>
      <a-form :model="byId" layout="vertical">
        <a-space :size="16" direction="vertical">
          <a-card class="general-card">
            <a-row :gutter="80">

              <a-col :span="8">
                <span>名称: {{ byId.name }}</span>
              </a-col>
            </a-row>

            <a-table :columns="showCol" :data="byId.hardwareSignalEntities"></a-table>

          </a-card>
        </a-space>
      </a-form>
    </a-modal>


  </div>

</template>

<script lang="ts" setup>
import {Pagination} from '@/types/global';
import {onMounted, reactive, ref} from "vue";
import {
  HardwareTypeById,
  HardwareTypeCreate,
  HardwareTypePage,
  HardwareTypeUpdate
} from "@/views/device/type/api";
import {Message} from "@arco-design/web-vue";

const showVisible = ref(false);
const showUpdateVisible = ref(false);
const byId = ref({});

const deleteVisible = ref(false);

const basePagination: Pagination = {
  current: 0,
  pageSize: 20,
};
const pagination = reactive({
  ...basePagination,
});
const columns = [
  {
    title: '类型名称',
    dataIndex: 'name',
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
  },

  {
    title: '信号名称',
    dataIndex: 'sig',
    slotName: "sig"
  },
  {
    title: "操作列",
    dataIndex: 'operations',
    slotName: 'operations',
  },
];
const columnsAdd = [
  {
    title: '信号名称',
    dataIndex: 'name',
    slotName: "name",

  }, {
    title: '系统信号标识',
    dataIndex: 'sysKey',
    slotName: 'sysKey',
  }, {
    title: '信号最小值',
    dataIndex: 'min',
    slotName: 'min',
  }, {
    title: '信号最大值',
    dataIndex: 'max',
    slotName: 'max',
  }, {
    title: '信号默认告警小区间',
    dataIndex: 'defaultWaringMin',
    slotName: 'defaultWaringMin',
  }, {
    title: '信号默认告警大区间',
    dataIndex: 'defaultWaringMax',
    slotName: 'defaultWaringMax',
  },
  {
    title: "操作列",
    dataIndex: 'operations',
    slotName: 'operations',
  },
];
const showCol = [
  {
    title: '信号名称',
    dataIndex: 'name',
    slotName: "name",

  }, {
    title: '系统信号标识',
    dataIndex: 'sysKey',
    slotName: 'sysKey',
  }, {
    title: '信号最小值',
    dataIndex: 'min',
    slotName: 'min',
  }, {
    title: '信号最大值',
    dataIndex: 'max',
    slotName: 'max',
  }, {
    title: '信号默认告警小区间',
    dataIndex: 'defaultWaringMin',
    slotName: 'defaultWaringMin',
  }, {
    title: '信号默认告警大区间',
    dataIndex: 'defaultWaringMax',
    slotName: 'defaultWaringMax',
  },
];
const formModel = ref({
  name: null
})
const renderData = ref([]);
const defaultRequest =
    {
      name: null,
      hardwareSignalEntities: []
    };
const createRequest = ref({
  name: null,
  hardwareSignalEntities: []
});
const updateRequest = ref({
  name: null,
  hardwareSignalEntities: []
});
const delArow = (recode) => {
  createRequest.value.hardwareSignalEntities.splice(recode, 1);
}
const delForUpdateArow = (recode) => {
  updateRequest.value.hardwareSignalEntities.splice(recode, 1);
}
const addForUpdateRow = () => {
  updateRequest.value.hardwareSignalEntities.push({
    name: "",
    sysKey: "",
    min: 0,
    max: 0,
    defaultWaringMin: 0,
    defaultWaringMax: 0,
  });
}
const addRow = () => {
  createRequest.value.hardwareSignalEntities.push({
    name: "",
    sysKey: "",
    min: 0,
    max: 0,
    defaultWaringMin: 0,
    defaultWaringMax: 0,
  });
}
const submitAdd = async () => {


  await HardwareTypeCreate(createRequest.value).then(
      (res) => {
        if (res.code == 20000) {
          Message.success("创建成功")
          createRequest.value = defaultRequest;
          fetchData();
        } else {
          Message.error("创建失败")
          createRequest.value = defaultRequest;
        }
      }
  )
  showVisible.value = false

}
const cancelAdd = () => {
  showVisible.value = false
  createRequest.value = defaultRequest;
}
const search = async () => {
  console.log(formModel.value);
  await fetchData()
}
const reset = async () => {
  await fetchData();
}
const onPageChange = async (current: number) => {

  pagination.current = current;
  let page = {
    size: pagination.pageSize,
    page: current - 1
  }
  let {data} = await HardwareTypePage(formModel.value, page)
  renderData.value = data.data;
  pagination.current = Number(data.page) + 1;
  pagination.total = Number(data.total);
};
const fetchData = async () => {
  try {
    let page = {
      size: pagination.pageSize,
      page: pagination.current == 0 ? 0 : pagination.current - 1,
    }
    let {data} = await HardwareTypePage(formModel.value, page);
    renderData.value = data.data;
    pagination.current = Number(data.page) + 1;
    pagination.total = Number(data.total);
  } catch (err) {
  } finally {
  }
}
const showAdd = async () => {
  showVisible.value = true;
}
const cancelUpdate = () => {
  updateRequest.value = {}
}
const submitUpdate = async () => {
  await HardwareTypeUpdate(updateRequest.value);
  updateRequest.value = {}
  showUpdateVisible.value = false;
}
const showViewVisible = ref(false);
const show = async (recode) => {
  let data = await HardwareTypeById(recode.id);
  byId.value = data.data
  showViewVisible.value = true

}
const showSee = () => {
  byId.value = {}
  showViewVisible.value = false;
}
const update = async (recode) => {
  console.log(recode);
  let data = await HardwareTypeById(recode.id);
  updateRequest.value = data.data
  showUpdateVisible.value = true
};
const delte = () => {

}
onMounted(() => {
  fetchData()
})

</script>

<style scoped>

</style>