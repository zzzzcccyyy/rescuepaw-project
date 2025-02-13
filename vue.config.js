const webpack = require('webpack');

module.exports = {
  configureWebpack: {
    plugins: [
      new webpack.DefinePlugin({
        __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: JSON.stringify(false)
      })
    ]
  },
  devServer: {
    port: 8080, // 将前端开发服务器端口设置为 8080
    proxy: {
      '/api': {
        target: 'http://localhost:8081', // 后端服务地址为 8081 端口
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      },
      '/animals': {
        target: 'http://localhost:8081',
        changeOrigin: true
      },
      '/activities': {
        target: 'http://localhost:8081',
        changeOrigin: true
      },
      '/forum/posts': {
        target: 'http://localhost:8081',
        changeOrigin: true
      }
    }
  }
};