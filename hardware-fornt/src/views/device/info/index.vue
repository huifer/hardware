<template>
  <div class="container">
    <Breadcrumb :items="['硬件管理', '硬件信息配置']" />
    <a-card class="general-card" title="硬件信息配置">
      <a-row>
        <a-col :flex="1">
          <a-form
              :model="formModel"
              :label-col-props="{ span: 6 }"
              :wrapper-col-props="{ span: 18 }"
              label-align="left"
          >
            <a-row :gutter="16">

              



            </a-row>
          </a-form>
        </a-col>
        <a-divider style="height: 84px" direction="vertical" />
        <a-col :flex="'86px'" style="text-align: right">
          <a-space direction="vertical" :size="18">
            <a-button type="primary" @click="search">
              <template #icon>
                <icon-search />
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
        <template #operations="{ record }">
          <a-button size="small" type="text" @click="show(record)">
            查看
          </a-button>
          <a-button size="small" type="text" @click="update(record)">
            修改
          </a-button>
          <a-button size="small" type="text" @click="delte(record)">
            删除
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
                    field="deviceName"
                    label="设备名称"
                >
                  <a-input
                      v-model="createRequest.deviceName"
                      placeholder="请输入设备名称"
                  >
                  </a-input>
                </a-form-item>
              </a-col>
              <a-col :span="8">
                <a-form-item
                    field="address"
                    label="设备地址"
                >
                  <a-input
                      v-model="createRequest.address"
                      placeholder="请输入设备地址"
                  >
                  </a-input>
                </a-form-item>
              </a-col>
              <a-col :span="8">
                <a-form-item
                    field="coordinates"
                    label="设备坐标"
                >
                  <a-input
                      v-model="createRequest.coordinates"
                      placeholder="请输入设备坐标"
                  >
                  </a-input>
                </a-form-item>
              </a-col>
              <a-col :span="8">
                <a-form-item
                    field="status"
                    label="设备状态"
                >
                  <a-input
                      v-model="createRequest.status"
                      placeholder="请输入设备状态"
                  >
                  </a-input>
                </a-form-item>
              </a-col>
            </a-row>
            <a-row style="margin-bottom: 16px">
              <a-col :span="12">
                <a-space>
                  <a-button type="primary" @click="addForRow">
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

            <a-table :columns="columnsAdd" :data="createRequest.extensionsEntities">
              <template #deviceId="{ rowIndex }">
                <a-input v-model="createRequest.extensionsEntities[rowIndex].deviceId"/>
              </template>
              <template #using="{ rowIndex }">
                <a-select v-model="createRequest.extensionsEntities[rowIndex].using"
                          :options="using"/>
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
        创建硬件类型
      </template>
      <a-form :model="updateRequest" layout="vertical">
        <a-space :size="16" direction="vertical">
          <a-card class="general-card">
            <a-row :gutter="80">

              <a-col :span="8">
                <a-form-item
                    field="deviceName"
                    label="设备名称"
                >
                  <a-input
                      v-model="updateRequest.deviceName"
                      placeholder="请输入设备名称"
                  >
                  </a-input>
                </a-form-item>
              </a-col>
              <a-col :span="8">
                <a-form-item
                    field="address"
                    label="设备地址"
                >
                  <a-input
                      v-model="updateRequest.address"
                      placeholder="请输入设备地址"
                  >
                  </a-input>
                </a-form-item>
              </a-col>
              <a-col :span="8">
                <a-form-item
                    field="coordinates"
                    label="设备坐标"
                >
                  <a-input
                      v-model="updateRequest.coordinates"
                      placeholder="请输入设备坐标"
                  >
                  </a-input>
                </a-form-item>
              </a-col>
              <a-col :span="8">
                <a-form-item
                    field="status"
                    label="设备状态"
                >
                  <a-input
                      v-model="updateRequest.status"
                      placeholder="请输入设备状态"
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

            <a-table :columns="columnsAdd" :data="updateRequest.extensionsEntities">
              <template #deviceId="{ rowIndex }">
                <a-input v-model="updateRequest.extensionsEntities[rowIndex].deviceId"/>
              </template>
              <template #using="{ rowIndex }">
                <a-select v-model="updateRequest.extensionsEntities[rowIndex].using"
                          :options="using"/>
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
  </div>

</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {Pagination} from "@/types/global";
import {HardwareInfoById, HardwareInfoPage} from "@/views/device/info/api";
import {HardwareTypePage} from "@/views/device/type/api";

const using = ref([
  {
    label: "使用",
    value: "true"
  }, {
    label: "未使用",
    value: "false"
  }
])
const showVisible = ref(false);
const createRequest = ref({
  deviceName: null,
  address: null,
  coordinates: null,
  status: null,
  extensionsEntities: [],

});
const updateRequest = ref({
  deviceName: null,
  address: null,
  coordinates: null,
  status: null,
  extensionsEntities: [],

});
const delArow = (recode) => {
  createRequest.value.extensionsEntities.splice(recode, 1);
};
const delForUpdateArow = (recode) => {
  updateRequest.value.extensionsEntities.splice(recode, 1);
};
const addForRow = () => {
  createRequest.value.extensionsEntities.push({
    using: "true",
    deviceId: "",

  })
};
const addForUpdateRow = () => {
  updateRequest.value.extensionsEntities.push({
    using: "true",
    deviceId: "",

  })
};

const showAdd = async () => {
  showVisible.value = true;
};
const renderData = ref([]);

const basePagination: Pagination = {
  current: 0,
  pageSize: 20,
};
const pagination = reactive({
  ...basePagination,
});

const formModel = ref({});
const columnsAdd = [{
  title: "生产商唯一标识",
  dataIndex: 'deviceId',
  slotName: 'deviceId',
}, {
  title: "是否使用",
  dataIndex: 'using',
  slotName: 'using',
}, {
  title: "操作列",
  dataIndex: 'operations',
  slotName: 'operations',
},]
const columns = [
  {
    title: "设备名称",
    dataIndex: 'deviceName',
    slotName: 'deviceName',
  }, {
    title: "设备地址",
    dataIndex: 'address',
    slotName: 'address',
  }, {
    title: "坐标",
    dataIndex: 'coordinates',
    slotName: 'coordinates',
  }, {
    title: "系统唯一id",
    dataIndex: 'uid',
    slotName: 'uid',
  }, {
    title: "状态",
    dataIndex: 'status',
    slotName: 'status',
  }, {
    title: "创建时间",
    dataIndex: 'createTime',
    slotName: 'createTime',
  }, {
    title: "操作列",
    dataIndex: 'operations',
    slotName: 'operations',
  },
];

const showUpdateVisible = ref(false);
const cancelUpdate = () => {
  showUpdateVisible.value = false;
  updateRequest.value = {};
}
const submitUpdate = () => {

}
const cancelAdd = () => {
  showVisible.value = false
  createRequest.value = {};

}
const submitAdd = () => {
  console.log(createRequest.value)
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
  let {data} = await HardwareInfoPage(formModel.value, page)
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
    let {data} = await HardwareInfoPage(formModel.value, page);
    renderData.value = data.data;
    pagination.current = Number(data.page) + 1;
    pagination.total = Number(data.total);
  } catch (err) {
  } finally {
  }
}
const show = async (roce) => {
  let {data} = await HardwareInfoById(roce.id)
  updateRequest.value = data.data;
}
const update = async (roce) => {
  showUpdateVisible.value = true;
  let {data} = await HardwareInfoById(roce.id)
  updateRequest.value = data.data;

}
const delte = () => {

}

onMounted(() => {
  fetchData()
})
</script>

<style scoped>

</style>