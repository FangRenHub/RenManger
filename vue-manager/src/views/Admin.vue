<script setup>
import { reactive, ref } from 'vue';
import { Search } from '@element-plus/icons-vue';
import request from '@/utils/request';
import { ElMessage, ElMessageBox } from 'element-plus';

//表单数据 start
const searchName = ref('');
const searchUsername = ref('');
const currentPage1 = ref(1);
const pageSize = ref(10);
const totalSize = ref(0);
const tableData = reactive({
  data:[]
})
//表单数据 end

//请求表单 start
const load = () => {
  request.get('admin/selectPage', {
    params: {
      pageNum: currentPage1.value,
      pageSize: pageSize.value,
      name: searchName.value,
      username: searchUsername.value
    }
  }).then(res => {
      if (res.code === '200'){
        totalSize.value = res.data.total;
        tableData.data = res.data.list;
      }
      else {
        ElMessage.error(res.msg);
      }
  })
}

const reset = () => {
  searchName.value = '';
  searchUsername.value = '';
  load();
}

load()
//请求表单 end

//表单操作 start
const handleAdd = () => {
  formVisible.value = true;
  form.username = '';
  form.name = '';
  form.phone = '';
  form.email = '';
}

const formVisible = ref(false);
const form = reactive({
  username: '',
  name: '',
  hone: '',
  email: ''
});
const formRef = ref();

const rules = reactive({
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
    { min: 3, max: 10, message: '账号长度在 3 到 10 个字符', trigger: 'blur' }
  ],
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' }
  ],
  phone: [
    { required: true, message: '请输入电话', trigger: 'blur' },
    { min: 11, max: 11, message: '电话长度为11位', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
  ]
});

const save = () => {
  console.log(form.id);
  form.id ? updateAdmin() : addAdmin();
}

const addAdmin = () => {
  formRef.value.validate((valid) => {
    if (valid){
      request.post('admin/add', form).then(res => {
        if (res.code === '200'){
          formVisible.value = false;
          ElMessage.success("添加成功");
          load()
        }
        else{
          ElMessage.error(res.msg);
        }
      })
    }
    else{
      ElMessage.error('请输入完整信息');
    }
  })
}

const handleEdit = (row) => {
  formVisible.value = true;
  form.id = row.id;
  form.username = row.username;
  form.name = row.name;
  form.phone = row.phone;
  form.email = row.email;
}

const updateAdmin = () => {
  formRef.value.validate((valid) => {
    if (valid){
      request.put('admin/update', form).then(res => {
        if (res.code === '200'){
          formVisible.value = false;
          ElMessage.success("修改成功");
          load()
        }
        else{
          ElMessage.error(res.msg);
        }
      })
    }
    else{
      ElMessage.error('请输入完整信息');
    }
  })
}

const handleDelete = (id) => {
  ElMessageBox.confirm('确定删除该管理员吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(() => {
    request.delete('admin/delete/' + id).then(res => {
      if (res.code === '200'){
        ElMessage.success("删除成功");
        load()
      }
      else{
        ElMessage.error(res.msg);
      }
    })
  }).catch(() => {
    ElMessage.info('已取消删除');
  })
}

const selection = reactive({
  data: []
});

const handleSelectionChange = (val) => {
  console.log(val);
  selection.data = val;
}

const deleteBatch = () => {
  console.log(selection);
  if (selection.data.length === 0){
    ElMessage.warning('请选择要删除的管理员');
    return;
  }
  ElMessageBox.confirm('确定删除选中的管理员吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(() => {
    request.delete('admin/deleteBatch', { data: selection.data }).then(res => {
      if (res.code === '200'){
        ElMessage.success("批量删除成功");
        load()
      }
      else{
        ElMessage.error(res.msg);
      }
    })
  }).catch(() => {
    ElMessage.info('已取消删除');
  })
}
//表单操作 end

</script>

<template>
  <div>
    <!--表单页面 start-->
    <div class="card" style="margin-top: 0;">
      <el-input clearable @clear="load" v-model="searchUsername" @keyup.enter="load" placeholder="输入用户名查询" :prefix-icon="Search" />
      <el-input clearable @clear="load" v-model="searchName" @keyup.enter="load" placeholder="输入姓名查询" :prefix-icon="Search" style="margin-left: 10px;" />
      <el-button type="primary" @click="load" style="margin-left: 10px;">查询</el-button>
      <el-button  @click="reset" style="margin-left: 10px;">重置</el-button>
    </div>
    <div class="card">
      <el-button type="primary" @click="handleAdd">新增</el-button>
      <el-button type="primary" style="margin-left: 10px;">导入</el-button>
      <el-button type="primary" style="margin-left: 10px;">导出</el-button>
      <div style="flex: 1;"></div>
      <el-button type="primary" style="margin-left: 10px;" @click="deleteBatch">删除</el-button>
    </div>
    <div class="card">
      <el-table :data="tableData.data" @selection-change="handleSelectionChange">
        <el-table-column type="selection" />
        <el-table-column prop="username" label="账号" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="phone" label="电话" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column label="操作" align="center"> 
          <template #default="scope">
          <el-button type="primary" icon="Edit" circle size="small" @click="handleEdit(scope.row)"></el-button>
          <el-button type="danger" icon="Delete" circle size="small" @click="handleDelete(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="card" style="margin-bottom: 0;">
      <el-pagination
        v-model:current-page="currentPage1"
        v-model:page-size="pageSize"
        :page-sizes="[5, 10, 20, 50]"
        layout="total, sizes,prev, pager, next"
        :total="totalSize"
        @size-change="load"
        @current-change="load"
    />
    </div>
    <!--表单页面 end-->

    <!--对话弹窗 start-->
    <el-dialog v-model="formVisible" title="新增管理员" width="500" destroy-on-close>
      <el-form ref="formRef" :rules="rules" :model="form" style="padding: 10px 30px 0px 30px">
        <el-form-item prop="username" label="账号" >
          <el-input v-model="form.username" autocomplete="off" />
        </el-form-item>
        <el-form-item prop="name" label="姓名">
          <el-input v-model="form.name" autocomplete="off" />
        </el-form-item>
        <el-form-item prop="phone" label="电话">
          <el-input v-model="form.phone" autocomplete="off" />
        </el-form-item>
        <el-form-item prop="email" label="邮箱">
          <el-input v-model="form.email" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="formVisible = false">取消</el-button>
          <el-button type="primary" @click="save">
            保存
          </el-button>
        </div>
      </template>
    </el-dialog>
    <!--对话弹窗 end-->
  </div>
</template>

<style scoped>
:deep(.el-table__header .cell){
  color: #241f29;
}

</style>