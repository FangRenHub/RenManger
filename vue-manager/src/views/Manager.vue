<script setup>
import { House, Location } from '@element-plus/icons-vue'
import { useRoute } from 'vue-router'

const route = useRoute()
</script>

<template>
    <div class="container">
        <!-- 头部区域 start -->
        <div class="header">
            <div class="logo">
                 <span class="logo-text">
                    R-MANAGER
                </span>
            </div>
            
            <div class="router-search">
                <span>
                    首页 / 数据分析
                </span>
            </div>

            <div style="flex: 1;"></div>
            
            <el-dropdown>
                <div class="el-dropdown-link user-info">
                    <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" alt="用户头像">
                    <span class="user-name">管理员</span>
                    <el-icon class="el-icon--right">
                        <arrow-down />
                    </el-icon>
                </div>
                <template #dropdown>
                    <el-dropdown-menu>
                    <el-dropdown-item>个人信息</el-dropdown-item>
                    <el-dropdown-item>修改密码</el-dropdown-item>
                    <el-dropdown-item>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>   
        </div>
        <!-- 头部区域 end -->

        <!-- 主体区域 start -->
        <div class="main">
            <!-- 侧边栏区域 start -->
            <div class="sidebar">
                <el-menu router :default-openeds="['1']" :default-active="route.path">
                    <el-menu-item index="/home">
                        <el-icon><House /></el-icon>
                        <span>首页</span>
                    </el-menu-item>
                    <el-sub-menu index="1">
                        <template #title>
                        <el-icon><location /></el-icon>
                        <span>数据管理</span>
                        </template>
                        <el-menu-item index="/test">item one</el-menu-item>
                    </el-sub-menu>
                </el-menu>
            </div>
            <!-- 侧边栏区域 end -->

            <!-- 主体内容区域 start -->
            <div class="main-content">
                <router-view></router-view>
            </div>
            <!-- 主体内容区域 end -->

        </div>
        <!-- 主体区域 end -->

    </div>
</template>

<style scoped>
.container {
    width: 100%;
    min-height: 100vh;
    box-sizing: border-box;
    padding: 10px;
    background-color: #f5f5f5;
}
.main {
    border-right: 2px solid #ffffff;
    border-radius: 0 0 10px 10px;
    display: flex;
    padding-top: 5px;
    box-shadow: inset -1px -1px 8px rgba(0,0,0,0.1);
}
.header {
    height: 50px;
    padding-left: 10px;
    background-color: #ffffff;
    display: flex;
    align-items: center;
    border-radius: 10px 10px 0 0;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.logo {
    width: 190px;
    color: #241f29;
    font-size: 18px;
    font-weight: 500;
}
.user-info {
    width: fit-content;
    display: flex;
    align-items: center;
    padding-right: 10px;
    cursor: pointer;
    outline:none;
}
.user-info img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 5px;
} 
.sidebar {
    width: 200px;
}
.el-menu {
    background-color: #ffffff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    min-height: calc(100vh - 75px);
    border-radius: 0 0 0 10px;
}
.el-menu .is-active{
    color: #0f87f7;
}  
.el-menu-item{
    font-size: 13px;
    height: 46px;
    color: #241f29;
}
.el-menu-item:hover{
    background-color: #cccccc;
}
:deep(.el-sub-menu__title){
    color: #241f29;
}
:deep(.el-sub-menu__title:hover){
    background-color: #cccccc;
}
.main-content{
    flex: 1;
    padding: 3px;
}
</style>
 