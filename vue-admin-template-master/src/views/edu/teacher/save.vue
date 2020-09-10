<template>
  <div class="app-container">
    <div style="padding: 0 10px">讲师表单</div>
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
      <el-input v-model="teacher.name"/></el-form-item>
      <el-form-item label="讲师排序">
      <el-input-number v-model="teacher.sort" controls-position="right" min="0">/></el-input-number></el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <!-- 数据类型必须和取出的json中的一致，否则无法回填，因此这里value使用动态绑定的值，保证其数据类型是number -->
          <el-option :value="1" label="高级讲师">
        <el-option :value="2" label="首席讲师"/></el-option></el-select>
      </el-form-item>
      <el-form-item label="讲师资历" >
      <el-input v-model="teacher.carrer"/></el-form-item>
      <el-form-item label="讲师简介" >
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>

      <!-- 讲师头像 TODO -->
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacherApi from '@/api/edu/teacher'
export default {
  data() {
    return {
      teacher: {
        name: '',
        sort: 0,
        level: 1,
        career: '',
        intro: '',
        avatar: ''
      },
      saveBtnDisabled: false
    }
  },
  watch: {
    // 监听路由变化
    $route(to, from) {
      this.init()
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
    // 有参且参数有ids
      if (this.$route.params && this.$route.params.id) {
      // 获取路径中id
        const id = this.$route.params.id
        // 根据id查讲师数据
        this.getInfo(id)
      } else {
        this.teacher = {}
      }
    },
    saveOrUpdate() {
      if (!this.teacher.id) {
        // 添加
        this.svaeTeacher()
      } else {
        // 修改
        this.updateTeacher()
      }
    },
    svaeTeacher() {
      teacherApi.addTeacher(this.teacher)
        .then(response => {
          // 添加成功
          this.$message({
            type: 'success',
            message: '添加成功!'
          })
          // 回到列表页
          this.$router.push({ path: '/teacher/table' })
        })
    },
    updateTeacher() {
      teacherApi.updateTeacher(this.teacher)
        .then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })

          // 回到列表页
          this.$router.push({ path: '/teacher/table' })
        })
    },
    getInfo(id) {
      teacherApi.getTeacherInfo(id)
        .then(response => {
          this.teacher = response.data.teacher
        })
    }
  }
}
</script>
