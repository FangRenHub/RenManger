<script setup>
import { reactive, ref } from 'vue';
import { Search } from '@element-plus/icons-vue';

const searchValue = ref('');
const currentPage1 = ref(1);
const pageSize = ref(10);
const totalSize = ref(0);
const tableData = reactive({
  data: [
    {name: '张三',phone: '12345678901',address: '北京市海淀区'},
    {name: '李四',phone: '12345678902',address: '上海市浦东新区'},
    {name: '王五',phone: '12345678903',address: '广州市天河区'},
    {name: '赵六',phone: '12345678904',address: '深圳市南山区'},
    {name: '孙七',phone: '12345678905',address: '湖南省长沙市'},
    {name: '周八',phone: '12345678906',address: '河北省石家庄市'},
    {name: '吴九',phone: '12345678907',address: '江苏省南京市'},
    {name: '郑十',phone: '12345678908',address: '浙江省杭州市'},
  ]
})

</script>

<template>
  <div>
    <div class="card">
      <el-input v-model="searchValue" placeholder="请输入查询内容" :prefix-icon="Search"  />
      <el-button type="primary" style="margin-left: 10px;">查询</el-button>
    </div>
    <div class="card">
      <el-button type="primary">新增</el-button>
      <el-button type="primary" style="margin-left: 10px;">导入</el-button>
      <el-button type="primary" style="margin-left: 10px;">导出</el-button>
      <el-button type="primary" style="margin-left: 10px;">删除</el-button>
    </div>
    <div class="card">
      <el-table :data="tableData.data">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="phone" label="电话" />
        <el-table-column prop="address" label="地址" />
      </el-table>
    </div>
    <div class="card">
      <el-pagination
        v-model:current-page="currentPage1"
        :page-size="pageSize"
        layout="total, prev, pager, next"
        :total="totalSize"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
    </div>
  </div>
</template>

<style scoped>
:deep(.el-table__header .cell){
  color: #241f29;
}

</style>