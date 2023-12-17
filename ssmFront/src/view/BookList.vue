<template>
  <el-card>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>馆藏类别管理</el-breadcrumb-item>
      <el-breadcrumb-item>馆藏图书管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row :gutter="10">
      <el-col :span="6"><el-input v-model="bname" clearable @change="bookList" cplaceholder="输入书名，鼠标点击外面搜索"></el-input></el-col>
      <el-col :span="6"><el-button class="addBookBtn" type="primary" @click="openAddDialog">+添加</el-button></el-col>
    </el-row>
    <el-table :data="tableData">
      <el-table-column prop="bid" label="编号" width="350"></el-table-column>
      <el-table-column prop="bname" label="书名" width="350"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
        <el-button type="success" @click="openEditDialog(scope.row)">编辑</el-button>
        <el-button type="danger" @click="remove(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
<!--    分页组件-->
    <div class="block">
      <span class="demonstration"></span>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pager.page"
        :page-sizes="[5,10,15,20]"
        :page-size="pager.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pager.total">
      </el-pagination>
    </div>
<!--    添加图书对话框-->
    <el-dialog title="添加图书" :visible.sync="addFlag">
      <el-form :model="book" :rules="rules" ref="addbook">
        <el-form-item label="图书名称" prop="bname">
          <el-input v-model="book.bname"></el-input>
        </el-form-item>
        <el-form-item label="图书作者" prop="author">
          <el-input v-model="book.author"></el-input>
        </el-form-item>
        <el-form-item label="图书价格" prop="price">
          <el-input v-model="book.price"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addFlag = false">取消</el-button>
        <el-button type="primary" @click="addSave">保存</el-button>
      </div>
    </el-dialog>
<!--    编辑图书对话框-->
    <el-dialog title="编辑图书" :visible.sync="editFlag">
      <el-form :model="book" :rules="rules" ref="editbook">
        <el-form-item label="图书名称" prop="bname">
          <el-input v-model="book.bname"></el-input>
        </el-form-item>
        <el-form-item label="图书作者" prop="author">
          <el-input v-model="book.author"></el-input>
        </el-form-item>
        <el-form-item label="图书价格" prop="price">
          <el-input v-model="book.price"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editFlag = false">取消</el-button>
        <el-button type="primary" @click="editSave()">保存</el-button>
      </div>
    </el-dialog>
  </el-card>
</template>

<style scoped>
.el-breadcrumb{
  padding: 10px;
  font-size: 15px;
}
.block{
  padding: 30px;
}
.addBookBtn{
  position: absolute;
  left: 1300px;
}
</style>
<script >
export default {
  data() {
    return {
      addFlag:false,
      editFlag:false,
      tableData: [],
      bid:'',
      bname:'',
      author:'',
      price:'',
      availability:'',
      isDeleted:'',
      book:{},//用来封装表单输入的数据
      rules:{
        bname:[{ required: true, message: '请输入图书名称', trigger: 'blur' }],
        author:[{ required: true, message: '请输入作者', trigger: 'blur' }],
        price:[{ required: true, message: '请输入价格', trigger: 'blur' }]
      },
      pager:{
        page:1,
        size:10,
        total:0,
      },
    }
  },
  methods: {
    handleSizeChange(val){
      this.pager.size=val
      this.bookList()
    },
    handleCurrentChange(val){
      this.pager.page=val
      this.bookList()
    },
    openEditDialog(row){
      this.editFlag=!this.editFlag
      this.book = row
    },
    openAddDialog(){
    this.addFlag=!this.addFlag
    },
    addSave(){
      // this.book.availability=0
      // this.book.isDeleted=0
      // this.book.bid=this.getNextBid()
      this.$refs['addbook'].validate(valid=>{
        if (valid){
          this.$http.post('http://localhost:8080/book/addBook',this.book).then(res=>{
            if(res.data.code == 200){
              this.$message({
                showClose: true,
                message: res.data.message,
                type: 'success'
              })
              this.openAddDialog();
              this.bookList()
            }
          }).catch(error=>{
            this.$message({
              showClose: true,
              message: '添加异常',
              type: 'error'
            });
          })
        }
      })
    },
    editSave(){
      this.$http.put('http://localhost:8080/book/editBook',this.book)
        .then(res=>{
          if (res.data.code == 200){
            this.$message({
              showClose: true,
              message: res.data.message,
              type: 'success'
            });
            this.editFlag=false
            this.bookList();
          }
        }).catch(error=>{
        this.$message({
          showClose: true,
          message: '编辑异常',
          type: 'error'
        });
      })
    },
    bookList() {
      this.$http.get('http://localhost:8080/book/BookList',{
        params:{
          page:this.pager.page,
          size:this.pager.size,
          bname:this.bname,
        }
      })
        .then(res => {
          if (res.data.code == 200)
          {this.tableData = res.data.data.data;
           this.pager.total = res.data.data.total}
        }).catch(error => {
        this.$message({
          showClose: true,
          message: '加载异常',
          type: 'error'
        });
      })
    },
    remove(row){
      this.$http.get('http://localhost:8080/book/deleteBook?bid=' + row.bid).then(res=>{
        if(res.data.code == 200){
          this.$message({
            showClose: true,
            message: res.data.message,
            type: 'success'
          });
          this.bookList()
        }
      }).catch(error=>{
        this.$message({
          showClose: true,
          message: '删除异常',
          type: 'error'
        });
      })
    },
  },
  mounted() {
    this.bookList();
  }
}
</script>
