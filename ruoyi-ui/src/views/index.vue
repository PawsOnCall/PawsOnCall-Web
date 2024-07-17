<template>
  <div class="app-container home">
    PawsOnCall
    <iframe src="/google-signin.html" frameborder="0" width="300" height="400"></iframe>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "Index",
  data() {
    return {
      version: "3.8.7"
    };
  },
  methods: {
    handleCredentialResponse(response) {
      axios.post('/dev-api/api/account/googleLogin', {credential: response.credential})
        .then(response => {
          if (response.data.code === 200) {
            alert('登录成功');
            localStorage.setItem('user', JSON.stringify(response.data.data));
          } else {
            alert('登录失败');
          }
        })
        .catch(error => {
          console.error('登录过程中出现错误:', error);
          alert('登录过程中出现错误，请稍后再试');
        });
    },
    receiveMessage(event) {
      if (event.data.credential) {
        this.handleCredentialResponse(event.data);
      }
    }
  },
  mounted() {
    window.addEventListener("message", this.receiveMessage, false);
  },
  beforeDestroy() {
    window.removeEventListener("message", this.receiveMessage);
  }
}
</script>

<style scoped lang="scss">
iframe {
  margin: 20px 0;
  display: block;
  border: none;
}
</style>
