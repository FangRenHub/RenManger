<template>
    <div class="not-found">
        <h1>404</h1>
        <p>抱歉，您访问的页面不存在</p>
        <router-link to="/" class="home-link">返回首页</router-link>
    </div>
</template>

<style scoped>
.not-found {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
    background-color: #f5f5f5;
}

h1 {
    font-size: 72px;
    color: #333;
    margin: 0;
}

p {
    color: #666;
    margin: 20px 0;
}

.home-link {
    color: #409EFF;
    text-decoration: none;
    padding: 10px 20px;
    border: 1px solid #409EFF;
    border-radius: 4px;
    transition: all 0.3s;
}

.home-link:hover {
    background-color: #409EFF;
    color: white;
}
</style>