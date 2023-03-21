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
                <icon-refresh />
              </template>
             重置
            </a-button>
          </a-space>
        </a-col>
      </a-row>
      <a-divider style="margin-top: 0" />

    </a-card>
  </div>

</template>

<script lang="ts" setup>
import {ref} from "vue";

const formModel = ref({

});
const search = () => {
}
const reset = () => {
}

</script>

<style scoped>

</style>