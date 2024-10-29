package com.example.listsearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var searchBox: EditText
    private lateinit var recyclerView: RecyclerView

    private val studentList = listOf(
        Student("Nguyễn An Bình", "20211234"),
        Student("Trần Bảo Ngọc", "20201345"),
        Student("Phạm Cường Khôi", "20221234"),
        Student("Lê Diệu Linh", "20211356"),
        Student("Hoàng Thị Thanh Em", "20191234"),
        Student("Võ Phát Đạt", "20202345"),
        Student("Đinh Thị Hồng Trang", "20221235"),
        Student("Ngô Văn Hưng", "20211478"),
        Student("Lý Bảo Linh", "20181234"),
        Student("Nguyễn Minh Châu", "20201122"),
        Student("Phan Ngọc Lan", "20221567"),
        Student("Đoàn Thị Kim Oanh", "20201654"),
        Student("Đặng Phương Thảo", "20191345"),
        Student("Nguyễn Đức Quân", "20221346"),
        Student("Trịnh Thị Quỳnh Mai", "20191423"),
        Student("Lâm Tuấn Sơn", "20211236"),
        Student("Trần Thảo Vy", "20201677"),
        Student("Lê Uyên Nhi", "20211456"),
        Student("Nguyễn Thị Bảo Vân", "20181333"),
        Student("Phạm Xuân Bình", "20201234")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBox = findViewById(R.id.searchBox)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        studentAdapter = StudentAdapter(studentList)
        recyclerView.adapter = studentAdapter

        // Add text listener to filter list based on input
        searchBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.length > 2) {
                    filterList(s.toString())
                } else {
                    studentAdapter.updateList(studentList)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterList(query: String) {
        val filteredList = studentList.filter {
            it.name.contains(query, ignoreCase = true) || it.studentId.contains(query)
        }
        studentAdapter.updateList(filteredList)
    }
}
