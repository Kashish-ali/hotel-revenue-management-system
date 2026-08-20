const money = n => new Intl.NumberFormat('en-IN',{style:'currency',currency:'INR',maximumFractionDigits:0}).format(n);

async function api(url, options) {
  const r = await fetch(url, options);
  const data = await r.json();
  if (!r.ok) throw new Error(data.error || 'Request failed');
  return data;
}

async function load() {
  const d = await api('/api/analytics/dashboard');
  document.querySelector('#revenue').textContent = money(d.totalRevenue);
  document.querySelector('#bookings').textContent = d.totalBookings;
  document.querySelector('#occupied').textContent = `${d.occupiedRooms}/${d.totalRooms}`;
  document.querySelector('#occupancy').textContent = d.occupancyRate.toFixed(1) + '%';

  const statusColor = s => s==='Available' ? 'style="color:#2F9E68"' : s==='Occupied'||s==='BOOKED' ? 'style="color:#C9A15C"' : 'style="color:#D64545"';
const rooms = await api('/api/rooms');document.querySelector('#rooms').innerHTML = rooms.map(r =>
    `<tr><td>${r.roomNumber} <small>#${r.id}</small></td><td>${r.roomType}</td><td>${money(r.basePrice)}</td><td ${statusColor(r.status)}>${r.status}</td></tr>`
  ).join('');

  const pricing = await api('/api/analytics/pricing');
  document.querySelector('#pricing').innerHTML = pricing.map(p =>
    `<div class="price"><b>${p.roomType}</b><strong>${money(p.suggestedPrice)}</strong><small>Base ${money(p.basePrice)} · Occupancy ${p.occupancyRate.toFixed(1)}%</small><div>${p.reason}</div></div>`
  ).join('');
}

document.querySelector('#customerForm').addEventListener('submit', async e => {
  e.preventDefault();
  try {
    const d = await api('/api/customers', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('phone').value
      })
    });
    customerMsg.textContent = `Created customer ID: ${d.id}`;
    e.target.reset();
  } catch (x) {
    customerMsg.textContent = x.message;
  }
});

document.querySelector('#bookingForm').addEventListener('submit', async e => {
  e.preventDefault();
  try {
    const d = await api('/api/bookings', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        customerId: +document.getElementById('customerId').value,
        roomId: +document.getElementById('roomId').value,
        checkIn: document.getElementById('checkIn').value,
        checkOut: document.getElementById('checkOut').value,
        guests: +document.getElementById('guests').value
      })
    });
    bookingMsg.textContent = `Booking created. Booking ID: ${d.id}`;
    e.target.reset();
    load();
  } catch (x) {
    bookingMsg.textContent = x.message;
  }
});

load().catch(e => console.error(e));