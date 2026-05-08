# 🏛️ The Great Relay: Messaging Architecture

This system is modeled after the Incan communication network—a high-performance, distributed relay designed for absolute reliability and speed. It serves as the dedicated transport and contract layer for our services.

---

## 🧱 The Core Trilogy

The architecture is divided into three distinct layers: Infrastructure, Logic, and Contract.

### 🛣️ Capac-Nan (The Road)

**The Infrastructure (NATS)**

Named after the "Great Road," **Capac-Nan** is the physical backbone of the system. It is the NATS cluster that provides the pathways for all data movement.

* **Purpose:** Message brokering, JetStream persistence, and KV storage.
* **Reliability:** Engineered to be permanent and resilient. If the road is clear, the message will reach its destination.

### 🏃 Chasqui (The Runner)

**The Logic (Producers/Consumers)**

The **Chasqui** are the active participants—the microservices. A Chasqui’s purpose is to wait for a message at a station (Subscriber), process it, and sprint to the next station to hand it off (Producer).

* **Purpose:** Event processing and NATS client implementation.
* **Prefix:** All messaging-related packages and logic use the `chasqui` namespace to denote active, relay-based movement.

### 🪢 Quipus (The Knots)

**The Contract (Protobuf)**

The **Quipus** are the knotted strings that carry the actual information. Without the knowledge of the knots, the messenger is just carrying string.

* **Purpose:** Centralized Schema Registry (Protobuf `.proto` files).
* **Implementation:** A single source of truth for data structures, ensuring that every Chasqui on the Capac-Nan speaks the exact same language.

---

## 🔄 The Relay Workflow

1. **Define the Knots:** New message structures are defined in the `quipus` registry.
2. **Prepare the Road:** Subjects and Streams are configured on the `capac-nan` (NATS).
3. **Deploy the Runner:** A `chasqui` service is deployed. It pulls the latest `quipus` definitions to understand how to serialize and deserialize the data pulses.
4. **Execute the Pulse:** Data is "knotted" into a binary payload and sprinted across the road.

---

## 🛠️ Technical Stack

* **Backbone:** NATS (JetStream + KV)
* **Protocols:** Protocol Buffers v3

---

> *"The system is a relay. Every runner must be fast, every road must be clear, and every knot must be precise."*