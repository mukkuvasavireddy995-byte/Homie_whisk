```javascript
// ==========================================
// HOMEWHISK PAYMENT PAGE
// ==========================================


// Load cart from localStorage

let cart = JSON.parse(
    localStorage.getItem("homewhiskCart")
) || [];


// Delivery charge

const DELIVERY_CHARGE = 40;


// ==========================================
// DISPLAY CART
// ==========================================

function displayCart() {

    const cartContainer =
        document.getElementById("cartItems");

    cartContainer.innerHTML = "";


    if (cart.length === 0) {

        cartContainer.innerHTML = `
            <p style="
                text-align:center;
                padding:20px;
                color:#777;
            ">
                Your cart is empty.
            </p>
        `;

        updateTotals();

        return;
    }


    cart.forEach((item, index) => {

        const name =
            item.name ||
            item.title ||
            "Bakery Item";


        const price =
            Number(item.price) || 0;


        const quantity =
            Number(item.quantity) ||
            Number(item.qty) ||
            1;


        const image =
            item.image ||
            item.img ||
            "https://via.placeholder.com/60";


        const itemTotal =
            price * quantity;


        cartContainer.innerHTML += `

            <div class="cart-item">

                <img
                    src="${image}"
                    alt="${name}"
                >

                <div class="cart-item-info">

                    <h4>${name}</h4>

                    <p>
                        ${quantity} × ₹${price}
                    </p>

                </div>

                <strong>
                    ₹${itemTotal}
                </strong>

            </div>

        `;

    });


    updateTotals();
}



// ==========================================
// CALCULATE TOTAL
// ==========================================

function calculateSubtotal() {

    return cart.reduce((total, item) => {

        const price =
            Number(item.price) || 0;

        const quantity =
            Number(item.quantity) ||
            Number(item.qty) ||
            1;

        return total + price * quantity;

    }, 0);

}


function updateTotals() {

    const subtotal =
        calculateSubtotal();


    const delivery =
        subtotal > 0
            ? DELIVERY_CHARGE
            : 0;


    const total =
        subtotal + delivery;


    document.getElementById("subtotal")
        .textContent = `₹${subtotal}`;


    document.getElementById("delivery")
        .textContent = `₹${delivery}`;


    document.getElementById("total")
        .textContent = `₹${total}`;

}



// ==========================================
// PAYMENT METHOD
// ==========================================

const paymentRadios =
    document.querySelectorAll(
        'input[name="payment"]'
    );


paymentRadios.forEach(radio => {

    radio.addEventListener(
        "change",
        function () {

            document
                .getElementById("upiForm")
                .classList.add("hidden");

            document
                .getElementById("cardForm")
                .classList.add("hidden");

            document
                .getElementById("codForm")
                .classList.add("hidden");


            if (this.value === "upi") {

                document
                    .getElementById("upiForm")
                    .classList.remove("hidden");

            }


            if (this.value === "card") {

                document
                    .getElementById("cardForm")
                    .classList.remove("hidden");

            }


            if (this.value === "cod") {

                document
                    .getElementById("codForm")
                    .classList.remove("hidden");

            }

        }
    );

});



// ==========================================
// CARD NUMBER FORMAT
// ==========================================

document
    .getElementById("cardNumber")
    .addEventListener("input", function () {

        let value =
            this.value
                .replace(/\D/g, "")
                .substring(0, 16);


        value =
            value.replace(
                /(.{4})/g,
                "$1 "
            )
            .trim();


        this.value = value;

    });



// ==========================================
// EXPIRY FORMAT
// ==========================================

document
    .getElementById("expiry")
    .addEventListener("input", function () {

        let value =
            this.value
                .replace(/\D/g, "")
                .substring(0, 4);


        if (value.length >= 3) {

            value =
                value.substring(0, 2)
                + "/"
                + value.substring(2);

        }


        this.value = value;

    });



// ==========================================
// PLACE ORDER
// ==========================================

function placeOrder() {

    // Check cart

    if (cart.length === 0) {

        alert(
            "Your cart is empty. Please add items first."
        );

        return;

    }


    // Customer details

    const name =
        document.getElementById("name")
            .value.trim();


    const phone =
        document.getElementById("phone")
            .value.trim();


    const email =
        document.getElementById("email")
            .value.trim();


    const address =
        document.getElementById("address")
            .value.trim();


    const city =
        document.getElementById("city")
            .value.trim();


    const pincode =
        document.getElementById("pincode")
            .value.trim();



    // Validation

    if (!name) {

        alert("Please enter your name.");

        return;

    }


    if (!/^[0-9]{10}$/.test(phone)) {

        alert(
            "Please enter a valid 10-digit mobile number."
        );

        return;

    }


    if (
        !email ||
        !email.includes("@")
    ) {

        alert(
            "Please enter a valid email."
        );

        return;

    }


    if (!address) {

        alert(
            "Please enter your delivery address."
        );

        return;

    }


    if (!city) {

        alert(
            "Please enter your city."
        );

        return;

    }


    if (!/^[0-9]{6}$/.test(pincode)) {

        alert(
            "Please enter a valid 6-digit PIN code."
        );

        return;

    }



    // Payment method

    const paymentMethod =
        document.querySelector(
            'input[name="payment"]:checked'
        ).value;



    // UPI validation

    if (paymentMethod === "upi") {

        const upi =
            document.getElementById("upi")
                .value.trim();


        if (
            !upi ||
            !upi.includes("@")
        ) {

            alert(
                "Please enter a valid UPI ID."
            );

            return;

        }

    }



    // Card validation

    if (paymentMethod === "card") {

        const cardNumber =
            document.getElementById("cardNumber")
                .value
                .replace(/\s/g, "");


        const expiry =
            document.getElementById("expiry")
                .value.trim();


        const cvv =
            document.getElementById("cvv")
                .value.trim();


        if (
            !/^[0-9]{16}$/.test(cardNumber)
        ) {

            alert(
                "Please enter a valid 16-digit card number."
            );

            return;

        }


        if (
            !/^[0-9]{2}\/[0-9]{2}$/.test(expiry)
        ) {

            alert(
                "Please enter a valid expiry date."
            );

            return;

        }


        if (
            !/^[0-9]{3}$/.test(cvv)
        ) {

            alert(
                "Please enter a valid CVV."
            );

            return;

        }

    }



    // ======================================
    // CREATE ORDER
    // ======================================

    const orderId =
        "HW" +
        Date.now()
            .toString()
            .slice(-8);


    const order = {

        orderId: orderId,

        customer: {

            name: name,

            phone: phone,

            email: email,

            address: address,

            city: city,

            pincode: pincode

        },

        items: cart,

        subtotal: calculateSubtotal(),

        delivery: DELIVERY_CHARGE,

        total:
            calculateSubtotal()
            + DELIVERY_CHARGE,

        paymentMethod:
            paymentMethod,

        createdAt:
            new Date().toISOString()

    };



    // Save order

    localStorage.setItem(
        "homewhiskLastOrder",
        JSON.stringify(order)
    );


    // Clear cart

    localStorage.removeItem(
        "homewhiskCart"
    );


    // Show success

    document.getElementById("orderId")
        .textContent = orderId;


    document
        .getElementById("successModal")
        .classList.remove("hidden");

}



// ==========================================
// GO HOME
// ==========================================

function goHome() {

    window.location.href =
        "index.html";

}



// ==========================================
// INITIALIZE
// ==========================================

displayCart();
```
