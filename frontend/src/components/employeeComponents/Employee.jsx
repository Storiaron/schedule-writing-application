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
    useEffect(()=> {
        fetchEmployeeInfo();
    }, [])
    return (
        <div></div>
    )
}

export default Employee;