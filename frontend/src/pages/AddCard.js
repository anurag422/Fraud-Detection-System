import { useState } from "react";
import API from "../services/api";
import "./AddCard.css";

function AddCard() {

  const [userId, setUserId] = useState("");
  const [cardNumber, setCardNumber] = useState("");
  const [expiry, setExpiry] = useState("");
  const [balance, setBalance] = useState("");

  const handleAddCard = async () => {
    try {
      await API.post("payment/addCard", {
        userId,
        cardNumber,
        expiry,
        balance
      });

      alert("Card Added ✅");

      setUserId("");
      setCardNumber("");
      setExpiry("");
      setBalance("");

    } catch (err) {
      console.log(err.response?.data);
      alert("Error adding card ❌");
    }
  };

  return (
    <div className="container">
      <div className="card">
        <h2>Add New Card</h2>
        <p className="subtitle">Enter card details</p>

        <input
          placeholder="User ID"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
        />

        <input
          placeholder="Card Number"
          value={cardNumber}
          onChange={(e) => setCardNumber(e.target.value)}
        />

        <input
          placeholder="Expiry (MM/YY)"
          value={expiry}
          onChange={(e) => setExpiry(e.target.value)}
        />

        <input
          type="number"
          placeholder="Balance"
          value={balance}
          onChange={(e) => setBalance(e.target.value)}
        />

        <button onClick={handleAddCard}>Add Card</button>
      </div>
    </div>
  );
}

export default AddCard;