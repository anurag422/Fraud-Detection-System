import { useState } from "react";
import API from "../services/api";
import "./Payment.css";

function Payment() {

  const [cardId, setCardId] = useState("");
  const [amount, setAmount] = useState("");
  const [location, setLocation] = useState("");

  const handlePayment = async () => {
    try {
      const res = await API.post("/payment/pay", {
        cardId,
        amount,
        location
      });

      alert("Status: " + res.data.status);

      // reset fields
      setCardId("");
      setAmount("");
      setLocation("");

    } catch (err) {
      alert("Payment Failed ❌");
    }
  };

  return (
    <div className="container">
      <div className="card">
        <h2>Make Payment</h2>
        <p className="subtitle">Enter payment details</p>

        <input
          placeholder="Card ID"
          value={cardId}
          onChange={(e) => setCardId(e.target.value)}
        />

        <input
          type="number"
          placeholder="Amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />

        <input
          placeholder="Location"
          value={location}
          onChange={(e) => setLocation(e.target.value)}
        />

        <button onClick={handlePayment}>Pay Now</button>
      </div>
    </div>
  );
}

export default Payment;