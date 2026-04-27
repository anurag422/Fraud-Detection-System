import { useEffect } from "react";
import axios from "axios";

function App() {

   useEffect(() => {
       axios.get("http://localhost:8080/auth/test")
         .then(res => console.log(res.data))
         .catch(err => console.log(err));
     }, []);

  return (
    <div className="App">
      <h1>Frontend Connected</h1>
    </div>
  );
}

export default App;
