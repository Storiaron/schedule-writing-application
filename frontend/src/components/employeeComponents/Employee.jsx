import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
function Employee(){
    const {employeeName} = useParams();
    const [employee, setEmployee] = useState(null);
    const fetchEmployeeInfo = async () => {
        const requestData = await fetch(`/api/employee/${employeeName}`);
        if(requestData.ok){
            const employeeData = await requestData.json();
            setEmployee(employeeData);
        }
    }
    const fetchEmployeeWorkDays = async () => {
        const requestData = await fetch(`/api/employee/${employeeName}`, {
            method: 'PUT',
            headers: {
              'Content-Type': 'application/json',
          },
            body: JSON.stringify(new Date())  
        });
        if(requestData.ok){
            const employeeData = await requestData.json();
            setEmployee(employeeData);
        }
        console.log(employee)
    }
    useEffect(()=> {
        fetchEmployeeInfo();
    }, [])
    return employee ? (
        <div>
            <div>{employee.name}</div>
            <button onClick={fetchEmployeeWorkDays}>Get workdays</button>
        </div>
    ) : (
        <div>Loading, please wait...</div>
    )
}

export default Employee;