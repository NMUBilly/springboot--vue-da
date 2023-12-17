<template>
  <el-card>
    <el-table :data="tableData">
      <el-table-column
        prop="bid"
        label="编号"
        width="350">
      </el-table-column>
      <el-table-column
        prop="bname"
        label="书名"
        width="350">
      </el-table-column>
      <el-table-column
        prop="price"
        label="价格">
      </el-table-column>
    </el-table>
  </el-card>
</template>

<style scoped>

</style>
<script>

export default {
  data() {
    return {
      tableData: []
    }
  },
  methods: {
    priceList() {
      this.$http.get('http://localhost:8080/book/PriceList')
        .then(res => {
          if (res.data.code == 200)
          this.tableData = res.data.data.data;
        }).catch(error => {
        alert('网络异常')
      })
    }
  },
  mounted() {
    this.priceList();
  }
}
</script>
