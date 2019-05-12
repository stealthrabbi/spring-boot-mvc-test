Vue.component('student-list', {
    template: `
    <div>
        <p>hello</p>
        <p>number of students {{studentList.length}}</p>
        <p>students:</p>
        <ul>
            <li v-for="student in studentList">
                <p>{{student.name}}</p>
                <p>{{student.dob}}</p>
                <p>{{student.ssn}}</p>
            </li>
        </ul>
    </div>
    `,
    data() {
        return {
            studentList: []
        }
    },
    mounted() {
       
        axios
            .get('students/list')
            .then(response => {
                this.studentList = response.data
                console.log('hello: ', response.data)
            })
    },
});