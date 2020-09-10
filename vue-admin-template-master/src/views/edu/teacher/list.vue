<template>
  <div class="app-container"><div style="padding: 10px 5px;">讲师列表</div>
    <!-- 查询表单 -->
    <el-form :inline="true" v-model="teacherQuery.name" class="demo-form-inline">
      <el-form-item label="讲师姓名" style="padding: 0px 10px">
        <el-input v-model="teacherQuery.name" placeholder="请输入讲师姓名"/>
      </el-form-item>
      <el-form-item label="讲师头衔" width="80px">
        <el-select v-model="teacherQuery.level" placeholder="讲师头衔">
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="teacherQuery.begin"
          value-format="yyyy-MM-dd HH:mm:ss"
          type="datetime"
          placeholder="开始日期"
          default-time="00:00:00"
          width="90px"/>
      </el-form-item>
      <el-form-item >
        <el-date-picker
          v-model="teacherQuery.end"
          value-format="yyyy-MM-dd HH:mm:ss"
          type="datetime"
          placeholder="结束日期"
          default-time="00:00:00"
          width="90px"/>
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <el-table
      :data="list"
      border
      fit
      stripe
      highlight-current-row
      style="width: 100%">
      <el-table-column
        label="序号"
        width="70"
        alight="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="80" />
      <el-table-column prop="level" label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level == 1 ? '高级讲师' : '首席讲师' }}
        </template>
      </el-table-column>

      <el-table-column prop="intro" label="资历"/>

      <el-table-column prop="gmtCreate" label="添加时间" width="160" />

      <el-table-column prop="sort" label="排序" width="60" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit" round>修改</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" round @click="removeById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0;text-align: center;"
      background
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />
  </div>

</template>

<script>
import teacherApi from '@/api/edu/teacher'
export default {
  data() {
    return {
      list: [], // 查询之后接口返回集合
      total: 0, // 总记录数
      page: 1, // 当前页
      limit: 5, // 每页数据量
      teacherQuery: {} // 条件对象
    }
  },
  created() {
    // 创建后调用
    this.getList()
  },
  methods: {
    // 讲师列表方法
    getList(page = 1) {
      this.page = page
      teacherApi
        .getTeacherList(this.page, this.limit, this.teacherQuery)
        .then((response) => {
          // 请求成功返回response
          this.list = response.data.rows
          this.total = response.data.total
        })
        .catch((error) => {
          console.log(error)
        })
    },
    resetData() {
      this.page = 1
      this.limit = 5
      this.teacherQuery = {}
      this.getList()
    },
    removeById(id) {
      this.$confirm('此操作将删除该教师信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        teacherApi.deleteTeacherId(id)
          .then(response => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            // 删除成功，提示信息并回到列表页面
            this.getList()
          })
      })
    }
  }
}
</script>

